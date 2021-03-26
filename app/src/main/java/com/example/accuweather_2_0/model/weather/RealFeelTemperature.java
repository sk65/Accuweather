package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

public class RealFeelTemperature {
    @SerializedName("Value")
    private double value;

    public double getValue() {
        return value;
    }
}
