package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

public class Minimum {
    @SerializedName("Value")
    private double value;

    public Minimum(double value) {
        this.value = value;
    }


    public double getValue() {
        return value;
    }
}