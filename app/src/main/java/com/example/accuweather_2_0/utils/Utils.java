package com.example.accuweather_2_0.utils;

import android.content.Context;
import android.location.Location;

import com.example.accuweather_2_0.App;
import com.example.accuweather_2_0.R;
import com.example.accuweather_2_0.model.weather.Temperature;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.accuweather_2_0.App.currentLocation;

public class Utils {
    public static int getWeatherIcon(int iconKey) {
        int iconRes = R.drawable.ic_weather_1;
        App.icons.keySet();
        for (int key : App.icons.keySet()) {
            if (iconKey == key) {
                iconRes = App.icons.get(iconKey);
            }
        }
        return iconRes;
    }

    public static String getCurrentDay(Context context, Date date, int position) {
        if (position == 0) {
            return context.getString(R.string.today);
        }
        if (position == 1) {
            return context.getString(R.string.tomorov);
        }
        return new SimpleDateFormat("EEEE").format(date.getTime());
    }

    public static String getTemperatureRange(Temperature temperature, Context context) {
        String degree = context.getString(R.string.degree);
        return Math.round(temperature.getMinimum().getValue())
                + degree + " / "
                + Math.round(temperature.getMaximum().getValue()) + degree;
    }

    public static String getLocationString() {
        return currentLocation.getLatitude() + "," + currentLocation.getLongitude();
    }
}
