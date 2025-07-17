import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Load data from csv
df_gasprices = pd.read_csv('Nat_Gas.csv')
# Convert 'Dates' column to datetime format
df_gasprices['Dates'] = pd.to_datetime(df_gasprices['Dates'], format='%m/%d/%y')
# Set 'Dates' as the index
df_gasprices.set_index('Dates', inplace=True)

# First glance of the original data
df_gasprices.plot(y='Prices', kind='line', marker='o', title='Price Over Time')
plt.xlabel('Date')
plt.ylabel('Price')
plt.grid(True)
plt.xticks(rotation=45)
plt.tight_layout()
plt.show()

# The time series model is: x(t) = m(t) + s(t) + z(t), where:
# x(t) is the gas price by time
# m(t) is the trend component of the price
# s(t) is the seasonal component of the price
# z(t) is the remaining noise
# The idea is to decompose the price into these components, then model corresponding components to form the price prediction

# Setting up some model parameters
d = 12 # Number of period in each cycle (12 months ~ 1 year)
q = int(d/2) # The extend of moving averages to each side
n = len(df_gasprices)

# First, the trend component m(t) is determined as the weighted moving average in 12-month windows
# Define custom weights 1/2d for the first and last elements, and 1/d for the others
weights = np.ones(d+1) * (1/d)
weights[0] = weights[-1] = 1 / (2*d) # This is due to even number of periods (12 months)

# Initialize an NaN list to store the weighted averages
df_gasprices['Trend'] = np.nan

# Iterate over the df to compute the weighted average
for i in range(q, n - q):
    window = df_gasprices.iloc[i - q:i + q + 1]  # Select the window of 2q + 1 elements
    weighted_avg = np.average(window['Prices'], weights=weights)  # Compute the weighted average
    df_gasprices.iloc[i, df_gasprices.columns.get_loc('Trend')] = weighted_avg

# Prices after the Trend component is eliminated (x(t) - m(t) = s(t) + z(t))
df_gasprices['exTrend'] = df_gasprices['Prices'] - df_gasprices['Trend']

# Next, the seasonal component s(t) is determined by averaging all the exTrend in the same phase of the cycle (eg. all January data is grouped together to take average, and so on)
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
            summ += df_gasprices.iloc[j]['exTrend']
            countt += 1

    # Add to the seasonal data record
    if countt > 0:
        seasonal.append(summ/countt) 

df_gasprices['Seasonal'] = np.nan 

# Replicate the seasonal data in the original timeline
for i in range(q, n - q):
    mod_value = i % 12
    seasonal_price = seasonal[mod_value - 6]
    df_gasprices.iloc[i, df_gasprices.columns.get_loc('Seasonal')] = seasonal_price

# Lastly, get the noises after eliminating the trend and seasonal components (x(t) - m(t) - s(t) = z(t))
df_gasprices['Noise'] = df_gasprices['exTrend'] - df_gasprices['Seasonal']

# Running regression on the linear trend component
from sklearn.linear_model import LinearRegression

# Save the original date (the start of time series)
original_date = df_gasprices.index[0]

# Convert the datetime index to the number of days since the first date
df_gasprices['days_since_start'] = (df_gasprices.index - original_date).days
df_trimmed = df_gasprices.iloc[q:-q].copy()

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

# Fitting the seasonal component
# y(t) = A*sin(B*t+C)+D
from scipy.optimize import curve_fit

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

# Fitting the random noise component
# Assuming that it follows a normal distribution
from scipy.stats import norm

# Fit a normal distribution to the data in column 'Noise'
mu, std = norm.fit(df_trimmed['Noise'])

# Assemble the function to predict gas price
def predict_gasprice(date):
    t = (date - original_date).days
    trend_component = model.predict([[t]])[0]
    seasonal_component = A * np.sin(B * t + C) + D
    noise_component = np.random.normal(mu, std)
    return trend_component + seasonal_component + noise_component

# Perform the price estimation on a chosen date (behind the original date)
input_date = pd.Timestamp('2025-01-31')
predicted_price = predict_gasprice(input_date)
print(predicted_price)

# Extend the index forward to 1y and perform estimation on the new index
extended_index = pd.date_range(df_gasprices.index[-1], periods=13, freq='M')[1:]

# Create a new DataFrame with the extended index
df_extended = pd.DataFrame(index=extended_index)

# Concatenate the original DataFrame with the new DataFrame
df_combined = pd.concat([df_gasprices, df_extended])

# Apply the price estimation
df_combined['predictedPrices'] = df_combined.index.to_series().apply(predict_gasprice)

# Plot the result
df_combined[['Prices', 'predictedPrices']].plot(figsize=(10, 6))

# Customize plot
plt.title('Observed and Estimated Gas Price Over Time')
plt.xlabel('Date')
plt.ylabel('Values')
plt.grid(True)
plt.legend(title='Columns')
plt.show()