package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

public class Day {
    @SerializedName("Icon")
    private int icon;

    @SerializedName("IconPhrase")
    private String iconPhrase;

    @SerializedName("PrecipitationType")
    private String precipitationType;

    public int getIcon() {
        return icon;
    }

    public Day() {
    }

    public Day(int icon, String iconPhrase, String precipitationType) {
        this.icon = icon;
        this.iconPhrase = iconPhrase;
        this.precipitationType = precipitationType;
    }

    public String getIconPhrase() {
        return iconPhrase;
    }

    public String getPrecipitationType() {
        return precipitationType;
    }

}
