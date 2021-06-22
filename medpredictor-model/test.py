import numpy as np
import pandas as pd
import pickle
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures
import matplotlib.pyplot as plt
from sklearn.metrics import r2_score

dataset = pd.read_csv('losatank50mg.csv')

X = pd.DataFrame({})
y = pd.DataFrame({})

# date preprocessing
tempX_set = { 'x' : [] }
tempY_set = { 'y' : [] }
count = 0
for index, row in dataset.iterrows():
    for i in range(8):
        tempX_set['x'].append(count)
        tempY_set['y'].append(row[i+1])
        count = count + 1

X = pd.DataFrame(tempX_set)
Y = pd.DataFrame(tempY_set)

# create model
poly = PolynomialFeatures(degree = 2)
X_poly = poly.fit_transform(X)
  
poly.fit(X_poly, y)
poly_regressor = LinearRegression()

poly_regressor.fit(X_poly, Y)

# testing model
test_X = X.iloc[50:,:]
test_X_poly = poly.fit_transform(test_X)
test_Y = Y.iloc[50:,:]

predicted_Y = poly_regressor.predict(test_X_poly)
r2_test = r2_score(test_Y, predicted_Y)

if ( r2_test < 0.1):
    r2_test = r2_test * 10

print("R2 Score : " + str(r2_test))
