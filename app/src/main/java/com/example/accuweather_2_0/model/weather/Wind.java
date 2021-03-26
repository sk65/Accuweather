package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("Speed")
    private Speed speed;
    @SerializedName("Direction")
    public Direction direction;
}
