package com.example.accuweather_2_0;

import android.app.Application;
import android.content.Context;
import android.location.Location;

import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    public final static Map<Integer, Integer> icons = new HashMap<Integer, Integer>() {{
        put(1, R.drawable.ic_weather_1);
        put(2, R.drawable.ic_weather_2);
        put(3, R.drawable.ic_weather_3);
        put(4, R.drawable.ic_weather_4);
        put(5, R.drawable.ic_weather_5);
        put(6, R.drawable.ic_weather_6);
        put(7, R.drawable.ic_weather_7);
        put(8, R.drawable.ic_weather_8);
        put(11, R.drawable.ic_weather_11);
        put(12, R.drawable.ic_weather_12);
        put(13, R.drawable.ic_weather_13);
        put(14, R.drawable.ic_weather_14);
        put(15, R.drawable.ic_weather_15);
        put(16, R.drawable.ic_weather_16);
        put(17, R.drawable.ic_weather_17);
        put(18, R.drawable.ic_weather_18);
        put(19, R.drawable.ic_weather_19);
        put(20, R.drawable.ic_weather_20);
        put(21, R.drawable.ic_weather_21);
        put(22, R.drawable.ic_weather_22);
        put(23, R.drawable.ic_weather_23);
        put(24, R.drawable.ic_weather_24);
        put(25, R.drawable.ic_weather_25);
        put(26, R.drawable.ic_weather_26);
        put(29, R.drawable.ic_weather_29);
        put(30, R.drawable.ic_weather_30);
        put(31, R.drawable.ic_weather_31);
        put(32, R.drawable.ic_weather_32);
        put(33, R.drawable.ic_weather_33);
        put(34, R.drawable.ic_weather_34);
        put(35, R.drawable.ic_weather_35);
        put(36, R.drawable.ic_weather_36);
        put(37, R.drawable.ic_weather_37);
        put(38, R.drawable.ic_weather_38);
        put(39, R.drawable.ic_weather_39);
        put(40, R.drawable.ic_weather_40);
        put(41, R.drawable.ic_weather_41);
        put(42, R.drawable.ic_weather_42);
        put(43, R.drawable.ic_weather_43);
        put(44, R.drawable.ic_weather_44);
    }};

    public static Location currentLocation;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
