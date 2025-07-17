import streamlit as st
import pandas as pd
import numpy as np
import plotly.express as px
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from scipy.optimize import curve_fit
from scipy.stats import norm
from typing import List, Tuple
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn import metrics
import seaborn as sns
from sklearn.tree import DecisionTreeClassifier, plot_tree # Import Decision Tree Classifier
from sklearn.model_selection import train_test_split # Import train_test_split function
from sklearn import metrics #Import scikit-learn metrics module for accuracy calculation

# Set page title
st.title("Credit Risk")
st.subheader("JPMorganChase Virtual Internship - Jun 2025")
st.write("Given the data on the loan borrowers, perform estimation of expected loss of a given customer. Based on FICO scores and a number of buckets, arrange the credit rating buckets to optimise log-linear probability of default.")

# 1. Load CSV
url = 'https://storage.googleapis.com/bucket-quickstart-theta-petal-2345276-i0/streamlit/VI/JPMorganChaseNatGas/Nat_Gas_Task_3_4_Loan_Data.csv'
try:
    df = pd.read_csv(url, delimiter=',')
    
except Exception as e:
    st.error(f"Error loading CSV: {e}")

st.header("1. Expected Loss Estimation")
st.write("Based on the data on the loan borrowers, estimate expected loss of a given customer using Logistic Regression and Decision Tree.")
st.subheader("Logistic Regression")

# 2. Logistic Regression - Expected Loss
# Split dataset in features and target variable
feature_cols = ['credit_lines_outstanding', 'loan_amt_outstanding', 'total_debt_outstanding', 'income','years_employed','fico_score']
X = df[feature_cols] # Features
y = df['default'] # Target variable

# Standardize the feature values
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

X_train, X_test, y_train, y_test = train_test_split(X_scaled, y, test_size=0.25, random_state=16)

# Model development and prediction
# Instantiate the model (using the default parameters)
logreg = LogisticRegression(random_state=16)

# Fit the model with data
logreg.fit(X_train, y_train)

# Predict the class labels for the test set
y_pred = logreg.predict(X_test)

# Predict the probabilities of default
probabilities = logreg.predict_proba(X_test)[:,1]

# Model Evaluation using Confusion Matrix
cnf_matrix = metrics.confusion_matrix(y_test, y_pred)

st.write("The Logistic Regression's Confusion Matrix:")
# Create a heatmap for the confusion matrix
fig, ax = plt.subplots()
sns.heatmap(cnf_matrix, annot=True, fmt='d', cmap='Blues', ax=ax)
ax.set_xlabel('Predicted')
ax.set_ylabel('Actual')
ax.set_title('Confusion Matrix')

# Display the plot in Streamlit
st.pyplot(fig)

st.write("The Confusion Matrix shows a relatively accurate model. Next we rank the data features by their influence on default prediction:")

# Importance value of features
coefficients = logreg.coef_[0]

coeff_df = pd.DataFrame({'Feature': feature_cols, 'Coefficient': coefficients})

# Display result=
coeff_sorted = coeff_df.sort_values(by='Coefficient', ascending=False)

# Display in Streamlit=
st.dataframe(coeff_sorted)

st.write(" It can be seen that the larger the number of credit lines outstanding, the more likely the borrower will default. This is followed by total debt outstanding, a larger debt amount is more likely to result in a default.")

# Calculate expected loss
def get_expected_loss(model, scaler, features):
    features_scaled = scaler.transform([features]) 

    # Estimate the probability of default from the model
    prob_of_default = model.predict_proba(features_scaled)[:,1][0]
    
    # Set at 10% as given
    recovery_rate = 0.1
    
    # Loss given default
    LGD = 1 - recovery_rate
    
    # Exposure at Default = loan_amt_standing
    EAD = features[1]
    
    expected_loss = EAD * LGD * prob_of_default
    
    return expected_loss

# Test data
sample_borrower = [5, 1234.56, 4567.89, 56789.00, 5, 555]

# Create DataFrame
df_sample = pd.DataFrame([sample_borrower], columns=feature_cols)

# Format currency columns with $ and thousand separator
df_sample['loan_amt_outstanding'] = df_sample['loan_amt_outstanding'].apply(lambda x: f"${x:,.2f}")
df_sample['total_debt_outstanding'] = df_sample['total_debt_outstanding'].apply(lambda x: f"${x:,.2f}")
df_sample['income'] = df_sample['income'].apply(lambda x: f"${x:,.2f}")

# Display in Streamlit
st.write("Lastly, given a sample borrower, estimate the expected loss (Table scrollable):")
st.dataframe(df_sample)

# Export Result
sample_expected_loss = get_expected_loss(logreg, scaler, sample_borrower)
st.write(f"Sample Expected Loss: ${sample_expected_loss:,.2f}.")

st.subheader("Decision Tree")

# Split dataset into training set and test set
# Here we don't need to scale X using normalisation
X_train_dt, X_test_dt, y_train_dt, y_test_dt = train_test_split(X, y, test_size=0.25, random_state=16) # 75% training and 25% test

# Create Decision Tree classifer object
clf = DecisionTreeClassifier()

# Train Decision Tree Classifer
clf = clf.fit(X_train_dt,y_train_dt)

#Predict the response for test dataset
y_pred_dt = clf.predict(X_test_dt)

# Refine the decision tree
# Create Decision Tree classifer object
clf_pr = DecisionTreeClassifier(criterion="gini", max_depth=3)

# Train Decision Tree Classifer
clf_pr = clf_pr.fit(X_train_dt,y_train_dt)

#Predict the response for test dataset
y_pred_dt_pr = clf_pr.predict(X_test_dt)

# Streamlit app
st.subheader("- Decision Tree (Unpruned)")

# Create a plot for the decision tree
fig, ax = plt.subplots(figsize=(12, 8))
plot_tree(clf, feature_names=X_train_dt.columns, class_names=['Class 0', 'Class 1'], filled=True, ax=ax)
plt.tight_layout()

# Display the plot in Streamlit
st.pyplot(fig)
st.write("Accuracy:",metrics.accuracy_score(y_test_dt, y_pred_dt))

# Streamlit app
st.subheader("- Decision Tree (Pruned)")

# Create a plot for the decision tree
fig, ax = plt.subplots(figsize=(12, 8))
plot_tree(clf_pr, feature_names=X_train_dt.columns, class_names=['Class 0', 'Class 1'], filled=True, ax=ax)
plt.tight_layout()

# Display the plot in Streamlit
st.pyplot(fig)
st.write("Accuracy:",metrics.accuracy_score(y_test_dt, y_pred_dt_pr))

# Model Evaluation using Confusion Matrix
cnf_matrix_dt_pr = metrics.confusion_matrix(y_test_dt, y_pred_dt_pr)

st.write("The Decision Tree's Confusion Matrix:")
# Create a heatmap for the confusion matrix
fig, ax = plt.subplots()
sns.heatmap(cnf_matrix_dt_pr, annot=True, fmt='d', cmap='Blues', ax=ax)
ax.set_xlabel('Predicted')
ax.set_ylabel('Actual')
ax.set_title('Confusion Matrix')

# Display the plot in Streamlit
st.pyplot(fig)

st.write("Again, The Confusion Matrix shows a relatively accurate model.")

# Calculate expected loss using Decision Tree estimation
def get_expected_loss_dt(model, features):
    
    # Estimate the probability of default from the model
    prob_of_default = model.predict_proba(features)[:,1][0]
    
    # Set at 10% as given
    recovery_rate = 0.1
    
    # Loss given default
    LGD = 1 - recovery_rate
    
    # Exposure at Default = loan_amt_standing
    EAD = features[0][1]
    
    expected_loss = EAD * LGD * prob_of_default
    
    return expected_loss

# Test data
sample_borrower_dt = np.array(sample_borrower).reshape(1, -1)

# Result
sample_expected_loss_dt = get_expected_loss_dt(clf_pr, sample_borrower_dt)

st.write("Given the same borrower, estimate the expected loss:")
st.write(f"Sample Expected Loss: ${sample_expected_loss_dt:,.2f}.")

st.header("2. Credit Rating Bucket Optimisation")
st.write("Based on the borrowers's FICO scores and default status, use dynamic programming to divide the FICO score range into a set number of credit rating bucket, while optimising the log-likelihood of default.")

# Pivotting the number of defaults and non-defaults by fico_score - sorted in ascending order
fico_df = (
    df.groupby('fico_score', as_index=False)['default']
      .agg(['sum', 'count'])                # sum = # of defaults, count = total rows
      .rename(columns={'sum': 'k', 'count': 'n'}) # where k: number of defaulted borrowers, n: total number of borrowers
      .sort_index()                         # ensure sorted by fico_score
)

# Add cumulative columns
fico_df['agg_k'] = fico_df['k'].cumsum()
fico_df['agg_n'] = fico_df['n'].cumsum()

# For this task the optimization of fico_score bucket structure basing on maximizing log-likelihood is implemented.

# Get the length of the df pivotted by fico_score
n = len(fico_df)

st.write("We start with pivoting the number of default and non-default borrowers by each FICO score. Next, we define a function (log_likelihood) to calculate the log-likelihood of default in a segment of the df, including the boundaries. Then recursive dynamic programming with memoization is applied to divide the buckets, each having dynamic left and right boundaries.")
# Calculate the log-likelihood of default in a segment of the df, including the boundaries
def log_likelihood(start_boundary: int, end_boundary: int):
    """Return the log-likelihood of default borrowers within two boundaries of fico_scores

    Args:
        start_boundary (int): The row number of the lower fico_score at the begin of the data subset
        end_boundary (int): The higher fico_score at the end of the data subset

    Returns:
        float: Log-likelihood value
    """
    # Nothing to calculate if boundaries are out of range
    if (start_boundary > end_boundary) | (start_boundary >= n) | (end_boundary < 0):
        return 0
    
    start_boundary = max(start_boundary, 0)
    end_boundary = min(end_boundary, n-1)
    
    # Count the number of defaults
    ki = fico_df['k'].iloc[start_boundary] + fico_df['agg_k'].iloc[end_boundary] - fico_df['agg_k'].iloc[start_boundary]
        
    # Count the number of borrowers
    ni = fico_df['n'].iloc[start_boundary] + fico_df['agg_n'].iloc[end_boundary] - fico_df['agg_n'].iloc[start_boundary]
    
    # Avoid log(0) by assigning 0 when probability is 0 or 1
    if ki == 0 or ki == ni:
        return 0
    
    pi = ki / ni
    return ki * np.log(pi) + (ni - ki) * np.log(1 - pi)

def calc_boundaries(r: int):
    # Handle edge case: not enough elements to partition
    if r > n:
        raise ValueError("Cannot partition into more parts than there are elements.")
    if r == 0:
        raise ValueError("Cannot partition with zero parts.")
    
    # Memoization table for log-likelihood values
    ll = np.full((n, r + 1), -np.inf)
    
    # Memoization table for storing the optimal split points
    # split_points[start][k] stores the optimal end point of the first bucket
    # when we have k buckets starting from position start
    split_points = np.full((n, r + 1), -1, dtype=int)

    def dp(start, k):
        """
        Returns the maximum log-likelihood when distributing k buckets within 
        the remaining df beginning from the left boundary start
        """
        # If already computed, return memoized result
        if ll[start, k] > -np.inf:
            return ll[start, k]

        if k == 1:
            # Base case: only one partition from start to end
            ll[start, k] = log_likelihood(start, n - 1)
            return ll[start, k]
        
        max_ll = -np.inf
        best_split = -1

        # Try every valid cut point from start to n - k
        # i is the ending boundary of the current bucket, expanding from left to right
        for i in range(start, n - k + 1):
            current_bucket_ll = log_likelihood(start, i)
            remaining_ll = dp(i + 1, k - 1)
            total_ll = current_bucket_ll + remaining_ll
            
            if total_ll > max_ll:
                max_ll = total_ll
                best_split = i
            
        ll[start, k] = max_ll
        split_points[start, k] = best_split
        
        return ll[start, k]

    # Compute optimal log-likelihood
    optimal_ll = dp(0, r)
    
    # Reconstruct the optimal boundaries by backtracking
    def reconstruct_boundaries(start, k):
        """Reconstruct the optimal boundaries from the memoized split points"""
        if k == 1:
            return [[start, n - 1]]
        
        # Get the optimal split point for this subproblem
        split_point = split_points[start, k]
        
        # Current bucket boundaries
        current_bucket = [start, split_point]
        
        # Recursively get remaining buckets
        remaining_buckets = reconstruct_boundaries(split_point + 1, k - 1)
        
        return [current_bucket] + remaining_buckets
    
    # Get the consecutive boundaries
    boundaries = reconstruct_boundaries(0, r)
    
    return optimal_ll, boundaries

# Test the function
st.write("Test the optimisation algorithm with the number of credit rating buckets r = 10. Lower rating indices imply higher credit quality:")
r = 10 # Set desired No of buckets
result = calc_boundaries(r)

# Replace the index boundaries with corresponding fico_scores
boundaries = np.empty(r, dtype=object)
B = [(fico_df.loc[i, 'fico_score'], fico_df.loc[j, 'fico_score']) for (i, j) in result[1]]

st.write("Optimal log-likelihood:", result[0])

# Create the DataFrame
display_df = pd.DataFrame(B, columns=['From', 'To'])

# Add a Rating column in reverse order to the 1-based index
display_df['Rating'] = range(len(display_df), 0, -1)
display_df = display_df.sort_values(by='Rating', ascending=True)

st.dataframe(display_df)

st.write("The larger the number of buckets r, the longer time it takes to form the optimal rating structure.")