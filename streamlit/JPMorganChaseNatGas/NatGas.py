import streamlit as st
import pandas as pd
import numpy as np
import plotly.express as px
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from scipy.optimize import curve_fit
from scipy.stats import norm
from typing import List, Tuple

# Set page title
st.title("Natural Gas Prices")
st.subheader("JPMorganChase Virtual Internship - Jun 2025")
st.write("Given a time series of purchasing prices on natural gas at the end of each month, perform price estimation and gas contract valuation.")

# 1. Load CSV
url = 'https://storage.googleapis.com/bucket-quickstart-theta-petal-2345276-i0/streamlit/VI/JPMorganChaseNatGas/Nat_Gas.csv'
try:
    df = pd.read_csv(url, delimiter=',')
    # Convert 'Dates' column to datetime format
    df['Dates'] = pd.to_datetime(df['Dates'], format='%m/%d/%y')
    # Set 'Dates' as the index
    df.set_index('Dates', inplace=True)
    
    # Plot Prices (x-axis) vs. Dates (y-axis)
    st.line_chart(df)
    
except Exception as e:
    st.error(f"Error loading CSV: {e}")

st.header("1. Time Series Analysis")
st.write("Perform decomposition of the gas prices, estimation for any date in the past, and prediction for the next 1 year.")
st.subheader("Gas Price Decomposition")

# 2. Time Series Decomposition

# The time series model is: x(t) = m(t) + s(t) + z(t), where:
# x(t) is the gas price by time
# m(t) is the trend component of the price
# s(t) is the seasonal component of the price
# z(t) is the remaining noise
# The idea is to decompose the price into these components, then model corresponding components to form the price prediction

# 2a. Setting model parameters
d = 12 # Number of period in each cycle (12 months ~ 1 year)
q = int(d/2) # The extend of moving averages to each side
n = len(df)

# 2b. The Trend Component m(t)
# It is determined as the weighted moving average in 12-month windows
# Define custom weights 1/2d for the first and last elements, and 1/d for the others
weights = np.ones(d+1) * (1/d)
weights[0] = weights[-1] = 1 / (2*d) # This is due to even number of periods (12 months)

# Initialize an NaN list to store the weighted averages
df['Trend'] = np.nan

# Iterate over the df to compute the weighted average
for i in range(q, n - q):
    window = df.iloc[i - q:i + q + 1]  # Select the window of 2q + 1 elements
    weighted_avg = np.average(window['Prices'], weights=weights)  # Compute the weighted average
    df.iloc[i, df.columns.get_loc('Trend')] = weighted_avg

# Prices after the Trend component is eliminated (x(t) - m(t) = s(t) + z(t))
df['exTrend'] = df['Prices'] - df['Trend']

# 2c. The Seasonal Component s(t)
# It is determined by averaging all the exTrend in the same phase of the cycle (eg. all January data is grouped together to take average, and so on)
seasonal = []

# Iterate over the df to compute the seasonal component
for i in range(q, q + d):
    mod_value = i % 12
    #print(mod_value)
    summ = 0
    countt = 0

    for j in range(i, n - q):
        # Get only prices in the same phase of the cycle
        if j % 12 == mod_value:
            #print('J la:'+str(j))
            summ += df.iloc[j]['exTrend']
            countt += 1

    # Add to the seasonal data record
    if countt > 0:
        seasonal.append(summ/countt) 

df['Seasonal'] = np.nan 

# Replicate the seasonal data in the original timeline
for i in range(q, n - q):
    mod_value = i % 12
    seasonal_price = seasonal[mod_value - 6]
    df.iloc[i, df.columns.get_loc('Seasonal')] = seasonal_price

# 2d. The Remaining Noises
# Lastly, get the noises after eliminating the trend and seasonal components (x(t) - m(t) - s(t) = z(t))
df['Noise'] = df['exTrend'] - df['Seasonal']

# 2e. Plotting The Price Decomposition
# Select 4 columns to plot (replace with your desired column names)
selected_columns = ['Prices', 'Trend', 'Seasonal', 'Noise']

# Define titles and colors for each selected column
titles = [f'{col} Plot' for col in selected_columns]
colors = ['blue', 'green', 'red', 'purple']  # Distinct colors for each plot

# Create a 2x2 subplot layout
fig, axes = plt.subplots(2, 2, figsize=(10, 8))

# Flatten axes for easier iteration
axes = axes.flatten()

# Plot each selected column
for i, (col, title, color) in enumerate(zip(selected_columns, titles, colors)):
    axes[i].plot(df[col], color=color)
    axes[i].set_title(title)
    axes[i].set_xlabel('Dates')
    axes[i].set_ylabel(col)
    # Rotate x-tick labels to prevent overlap
    axes[i].tick_params(axis='x', rotation=45)
    # Add horizontal and vertical gridlines (not too dense)
    axes[i].grid(True, which='both', linestyle='--', linewidth=0.5, alpha=0.7)

# Adjust layout to prevent overlap
plt.tight_layout()

# Display the plot in Streamlit
st.pyplot(fig)

# 3. Fitting Price Components
# Linear Regression For The Trend Component
# Save the original date (the start of time series)
original_date = df.index[0]

# Convert the datetime index to the number of days since the first date
df['days_since_start'] = (df.index - original_date).days
df_trimmed = df.iloc[q:-q].copy()

# Prepare the feature (X) and target (y) variables
X_train = df_trimmed[['days_since_start']]
y_train = df_trimmed['Trend']

# Initialize and fit the linear regression model
model = LinearRegression()
model.fit(X_train.values, y_train.values)

# Function to predict the value of A for any given date
def predict_trend(date):
    # t = number of days from the origin
    t = (date - original_date).days
    return model.predict([[t]])[0]

# The Seasonal Component
# y(t) = A*sin(B*t+C)+D

# Define the sine function
def sine_func(t, A, B, C, D):
    return A * np.sin(B * t + C) + D

# Fit the sine function to the data
params, _ = curve_fit(sine_func, df_trimmed['days_since_start'], df_trimmed['Seasonal'], p0=[10, 2 * np.pi / 365, 0, 120])

# Extract the fitted parameters
A, B, C, D = params

def predict_seasonal(date):
    t = (date - original_date).days
    return A * np.sin(B * t + C) + D

# The Random Noise Component
# Assuming that it follows a normal distribution

# Fit a normal distribution to the data in column 'Noise'
mu, std = norm.fit(df_trimmed['Noise'])

# 4. Assemble The Function To Predict Gas Price
def predict_gasprice(date):
    t = (date - original_date).days
    trend_component = model.predict([[t]])[0]
    seasonal_component = A * np.sin(B * t + C) + D
    noise_component = np.random.normal(mu, std)
    return trend_component + seasonal_component + noise_component

# 5. Perform 1Y Forward Price Estimation
# Extend the index forward to 1y and perform estimation on the new index
extended_index = pd.date_range(df.index[-1], periods=13, freq='M')[1:]

# Create a new DataFrame with the extended index
df_extended = pd.DataFrame(index=extended_index)

# Concatenate the original DataFrame with the new DataFrame
df_combined = pd.concat([df, df_extended])

# Apply the price estimation
df_combined['predictedPrices'] = df_combined.index.to_series().apply(predict_gasprice)

# 6. Plot The Gas Prices And Predicted Gas Prices
import plotly.graph_objects as go

st.subheader("Gas Price Estimation & Prediction")
st.write("- The Trend Component: Linear Regression")
st.write("- The Seasonal Component: Periodical Averaged")
st.write("- The Noise Component: Normal Distribution Simulation")
st.write("- Forecast Window: 1 Year")

# Create a Plotly figure
fig = go.Figure()

# Add line A (red, dashed)
fig.add_trace(go.Scatter(
    x=df_combined.index,
    y=df_combined['predictedPrices'],
    name='Predicted Prices',
    line=dict(color='red', dash='dash')
))

# Add line B (blue, solid)
fig.add_trace(go.Scatter(
    x=df_combined.index,
    y=df['Prices'],
    name='Prices',
    line=dict(color='blue')
))

# Update layout for better appearance
fig.update_layout(
    xaxis_title='Dates',
    yaxis_title='Prices',
    template='plotly_dark'  # Optional: dark theme for visibility
)

# Use a container and CSS to center the chart vertically
with st.container():
    # Add custom CSS to center the chart vertically
    st.markdown(
        """
        <style>
        .css-1v0mbdj {  /* Targets Streamlit's plot container */
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;  /* Full viewport height */
        }
        </style>
        """,
        unsafe_allow_html=True
    )
    # Display the Plotly chart
    st.plotly_chart(fig, use_container_width=True)

st.header("2. Natural Gas Contract Valuation")
st.write("Write a function that is able to use the price data created previously to price the contract. The client may want to choose multiple dates to inject and withdraw a set amount of gas. Consider all the cash flows involved in the product.")
st.write("The input parameters that should be taken into account for pricing are:")
st.write("- Injection dates.")
st.write("- Withdrawal dates.")
st.write("- The prices at which the commodity can be purchased/sold on those dates.")
st.write("- The rate at which the gas can be injected/withdrawn.")
st.write("- The maximum volume that can be stored.")
st.write("- Storage costs.")
st.write("Assumptions:")
st.write("- Storage cost per month: $50,000.")
st.write("- Injection/Withdrawal cost: $10,000/mln MMBtu.")
st.write("- The max storage volume: 5,000,000 MMBtu.")
st.write("A sample of injection/withdrawal schedule:")
# Task 2
# Assumptions:
# 1. Daily prices corresponding to injections/withdrawals are estimated using the mechanism built in Task 1. Unit: $ per MMBtu
# A logical assumption is these dates all happen in the future (after the last date of available data). However, should an old date be entered, a corresponding historical price would be used as well.
# 2. The opening stored volume is 0 MMBtu. Assuming that the storage rental cost starts to count on the same day with the first injection (Could be rented earlier but we simplify the case).
# 3. The storage cost is fixed from the first injection to the month of contract valuation, volume-independent, with the number of rental months rounded up - similar to house rental. Unit: $ per month.
# 4. The contract value consists of three part: 1/ The potential value of the gas stored in the tank, 2/ The realized value in cash of the gas already withdrew, and 3/ Related costs.
# 5. Since the interest rate is assumed to be 0%, assume that we don't reinvest the cash part.
# 6. Since there is no delay in transportation, we assume that the cost of transportation is $0.
# 7. The rate at which the gas can be injected/withdrawn = The cost per injection/withdrawal per million MMBtu (volume depended).
# 8. The valuation is calculated up to the remaining gas on the date of the last withdrawal/injection.

# 1. A new function to accomodate assumption #1
def get_price(date):
    last_date = df.index[-1]
    # If the input date is found inside the price data -> use the data
    if (date in df.index) & (date <= last_date):
        return df.loc[date, 'Prices']
    # If not, use the estimation
    else:
        return predict_gasprice(date)

def price_gas_contract(
    injection_schedule: List[Tuple[str, float]],  # List of (date, volume)
    withdrawal_schedule: List[Tuple[str, float]],
    get_price,  # function(date) -> float
    storage_cost_per_month: float,  # in USD/month
    inj_wdr_cost_per_mln_mmbtu: float,  # USD per million MMBtu
    max_storage_volume: float
) -> float:
    """
    Prices a gas contract given injections, withdrawals, and market/storage parameters.

    Args:
        injection_schedule: List of (date, volume in MMBtu)
        withdrawal_schedule: List of (date, volume in MMBtu)
        get_price: function(date) -> price per MMBtu
        storage_cost_per_month: constant USD/month
        inj_wdr_cost_per_mmbtu: same cost for injection & withdrawal
        max_storage_volume: max storage capacity in MMBtu

    Returns:
        Contract value in USD
    """
    # Convert dates to datetime
    inj_schedule = [(pd.to_datetime(date), vol) for date, vol in injection_schedule]
    wdr_schedule = [(pd.to_datetime(date), vol) for date, vol in withdrawal_schedule]

    # Merge and sort all event dates
    all_dates = sorted(set(date for date, _ in inj_schedule + wdr_schedule))
    
    # Initiate sums
    inventory = 0.0
    purchase_cost = 0.0
    realisation_held_in_cash = 0.0
    inj_wdr_cost = 0.0
    storage_cost = 0.0

    # Calculate storage cost
    if all_dates:
        # Generate prices by dates
        price_df = pd.DataFrame({'Date': all_dates,'Price': [get_price(date) for date in all_dates]}).set_index('Date')
        
        first_date = min(all_dates)
        last_date = max(all_dates)
        
        # Get the number of months owing the storage cost, rounded up to the nearest integer
        year_diff = last_date.year - first_date.year
        month_diff = last_date.month - first_date.month
        day_diff = last_date.day - first_date.day

        storage_months = year_diff * 12 + month_diff

        # Round up if there are remaining days
        if day_diff > 0:
            storage_months += 1
        
        storage_cost -= storage_months * storage_cost_per_month

        # Process injections and withdrawals by date
        for date in sorted(all_dates):
            # Get the price on the date 'date'
            price = price_df.loc[date, 'Price']
            
            # Injection
            # Look up the injection volume corresponding to the date 'date'
            inj_vol = next((vol for d, vol in inj_schedule if d == date), 0.0)
            
            # Adjust injection volume to not exceeding the maximum storage capacity
            if inventory + inj_vol > max_storage_volume:
                inj_vol = max(0, max_storage_volume - inventory)
                
            if inj_vol > 0:
                # Each injection decreases the contract value corresponding to the purchase cost
                purchase_cost -= inj_vol * price
                # Injection cost, converted
                inj_wdr_cost -= inj_vol * inj_wdr_cost_per_mln_mmbtu / 1e6
                # Update inventory
                inventory += inj_vol

            # Withdrawal
            # Look up the withdrawal volume corresponding to the date 'date'
            wdr_vol = next((vol for d, vol in wdr_schedule if d == date), 0.0)

            # Adjust withdrawal volume to not exceeding the available stored volume
            if inventory - wdr_vol < 0:
                wdr_vol = inventory
                
            if wdr_vol > 0:
                # Each withdrawal realises a part of the stored gas to cash with the selling price on that same day
                realisation_held_in_cash += wdr_vol * price
                # Withdrawal cost, converted
                inj_wdr_cost -= wdr_vol * inj_wdr_cost_per_mln_mmbtu / 1e6
                # Update inventory
                inventory -= wdr_vol
            
        # Valuation of the remaining gas in the end, assuming the price on that same day
        remaining_value = inventory * price_df.loc[last_date, 'Price']

    return purchase_cost + realisation_held_in_cash + inj_wdr_cost + storage_cost + remaining_value

# Inputs
injections = [("2021-02-28", 1e6), ("2021-05-31", 400000),("2023-01-31", 400000)]
withdrawals = [("2021-04-30", 500000), ("2021-06-30", 200000),("2022-10-31", 350000),("2021-10-31", 350000)]

value = price_gas_contract(
    injection_schedule=injections,
    withdrawal_schedule=withdrawals,
    get_price=get_price,
    storage_cost_per_month=50000,
    inj_wdr_cost_per_mln_mmbtu=10000,  # $10K per million MMBtu
    max_storage_volume=5e6
)

# Display injections and withdrawals on streamlit
# Create DataFrames for injections and withdrawals
inj_df = pd.DataFrame(injections, columns=['Date', 'Injection'])
wd_df = pd.DataFrame(withdrawals, columns=['Date', 'Withdrawal'])

# Convert Date columns to datetime
inj_df['Date'] = pd.to_datetime(inj_df['Date'])
wd_df['Date'] = pd.to_datetime(wd_df['Date'])

# Merge DataFrames on Date, using outer join to include all dates
df = pd.merge(inj_df, wd_df, on='Date', how='outer')

# Sort by Date
df = df.sort_values('Date')

# Fill NaN values with 0 for clarity
df = df.fillna(0)

# Format dates for display
df['Date'] = df['Date'].dt.strftime('%Y-%m-%d')

# Format numbers with 1,000 separators
df['Injection'] = df['Injection'].apply(lambda x: f"{int(x):,}" if x != 0 else "0")
df['Withdrawal'] = df['Withdrawal'].apply(lambda x: f"{int(x):,}" if x != 0 else "0")

# Apply right alignment to Injection and Withdrawal columns
styled_df = df.style.set_properties(**{'text-align': 'right'}, subset=['Injection', 'Withdrawal'])

# Display the styled table in Streamlit
st.dataframe(styled_df)

st.write(f"Contract Value: ${value:,.2f}")