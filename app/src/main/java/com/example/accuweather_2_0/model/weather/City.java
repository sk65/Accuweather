package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

public class City {

    @SerializedName("Key")
    private String key;

    @SerializedName("LocalizedName")
    public String localizedName;

    public String getLocalizedName() {
        return localizedName;
    }

    public String getKey() {
        return key;
    }
}
