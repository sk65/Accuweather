package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

public class AirAndPollen {

    @SerializedName("Name")
    private String name;
    @SerializedName("Value")
    private int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
