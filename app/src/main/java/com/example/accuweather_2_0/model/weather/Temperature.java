package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("Minimum")
    private Minimum minimum;

    @SerializedName("Maximum")
    private Maximum maximum;

    public Temperature() {
    }

    public Temperature(Minimum minimum, Maximum maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Minimum getMinimum() {
        return minimum;
    }

    public Maximum getMaximum() {
        return maximum;
    }


}
