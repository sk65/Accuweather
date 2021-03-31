package com.example.accuweather_2_0.model.enams;

public enum AirAndPollenNames {
    AIR_QUALITY("AirQuality");
    private final String name;

    AirAndPollenNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
