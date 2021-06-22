import numpy as np
import pandas as pd
import pickle
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures
import matplotlib.pyplot as plt

dataset = pd.read_csv('niffidipine20er.csv')

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

print(count)

X = pd.DataFrame(tempX_set)
Y = pd.DataFrame(tempY_set)

#lin_regressor = LinearRegression()
#lin_regressor.fit(X, Y)

#plt.scatter(X,Y, color='red')
#plt.plot(X, lin_regressor.predict(X),color='blue')
#plt.title("Medicine Order Rate Linear")
#plt.xlabel('Qunatity')
#plt.ylabel('Year and Month')
#plt.show()

# create model
poly = PolynomialFeatures(degree = 4)
X_poly = poly.fit_transform(X)
  
poly.fit(X_poly, y)
poly_regressor = LinearRegression()

poly_regressor.fit(X_poly, Y)

# plot model
plt.scatter(X,Y, color='red')
plt.plot(X, poly_regressor.predict(poly.fit_transform(X)),color='blue')
plt.title("Niffidipine 20 ER Purchase Rate")
plt.ylabel('Qunatity')
plt.xlabel('Months starts for Jan 2013')
plt.show()

