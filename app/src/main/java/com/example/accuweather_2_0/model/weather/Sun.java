package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Sun {
    @SerializedName("Rise")
    private Date rise;

    @SerializedName("Set")
    private Date set;

    public Date getRise() {
        return rise;
    }

    public void setRise(Date rise) {
        this.rise = rise;
    }

    public Date getSet() {
        return set;
    }

    public void setSet(Date set) {
        this.set = set;
    }
}
