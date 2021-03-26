package com.example.accuweather_2_0.model.weather;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Date;


public class DailyForecast {
    @SerializedName("Date")
    private Date date;

    @SerializedName("Temperature")
    private Temperature temperature;

    @SerializedName("Day")
    private Day day;

    public DailyForecast() {
    }



    public Date getDate() {
        return date;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Day getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "DailyForecast{" +
                "date=" + date +
                ", temperature=" + temperature +
                ", day=" + day +
                '}';
    }
}
