package com.example.myapi;

import com.google.gson.annotations.SerializedName;

public class DataModel {

//{"USD":1,"EGP":15.72,"EUR":0.83,"GBP":0.73,"SAR":3.75,"AED":3.67,"KWD":0.3}

    private double USD;
    private double EGP;
    private double EUR;
    private double GBP;
    private double SAR;
    private double AED;
    private double KWD;

    public double getUSD() {
        return USD;
    }

    public double getEGP() {
        return EGP;
    }

    public double getEUR() {
        return EUR;
    }

    public double getGBP() {
        return GBP;
    }

    public double getSAR() {
        return SAR;
    }

    public double getAED() {
        return AED;
    }

    public double getKWD() {
        return KWD;
    }
}
