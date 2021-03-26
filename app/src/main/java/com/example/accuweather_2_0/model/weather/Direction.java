package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

public class Direction {
    @SerializedName("English")
    private String direction;

    public String getDirection() {
        return direction;
    }
}
