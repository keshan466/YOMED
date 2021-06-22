import numpy as np
from flask import Flask, request, jsonify, render_template
import pickle
from sklearn.preprocessing import PolynomialFeatures
from flask_cors import CORS, cross_origin

app = Flask(__name__)
cors = CORS(app)

#get prediction of each medicine end point  
@app.route('/predict',methods=['POST'])
def predict():
    data = request.get_json()
    model = None
    poly = None

    medicine = data['medicine']

    # load relevant model
    if medicine == 'adorastatin':
        poly = PolynomialFeatures(degree = 3)
        model = pickle.load(open('adorastatin_model.pkl', 'rb'))
    elif medicine == 'aspirin':
        poly = PolynomialFeatures(degree = 4)
        model = pickle.load(open('aspirin_model.pkl', 'rb'))
    elif medicine == 'losatank':
        poly = PolynomialFeatures(degree = 2)
        model = pickle.load(open('losatank_model.pkl', 'rb'))
    elif medicine == 'metformin':
        poly = PolynomialFeatures(degree = 2)
        model = pickle.load(open('metformin_model.pkl', 'rb'))
    elif medicine == 'niffidipine':
        poly = PolynomialFeatures(degree = 4)
        model = pickle.load(open('niffidipine_model.pkl', 'rb'))     
    else:
        return jsonify({'success' : False, 'error' : 'Invalid Medicine'})


    x = int(data['month']) + ((int(data['year']) - 2013)*12) - 1

    #predict the value
    prediction = model.predict(poly.fit_transform([[x]]))
    output = prediction[0][0]

    if(data['withCovid'] and  medicine == 'aspirin'):
        output = output + output*0.4

    if(output < 0):
        output = output * (-1)

    return jsonify({'success' : True, 'prediction' : output})


if __name__ == "__main__":
    app.run(debug=True)
