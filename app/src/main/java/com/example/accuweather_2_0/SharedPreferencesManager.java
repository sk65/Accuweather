package com.example.accuweather_2_0;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.accuweather_2_0.model.screen.DetailsScreenModel;
import com.example.accuweather_2_0.model.screen.MainScreenModel;
import com.example.accuweather_2_0.model.weather.City;
import com.google.gson.Gson;


public class SharedPreferencesManager {

    private static final String PACKAGE_NAME = "com.example.lesson8";
    private static final String PREF_KEY = PACKAGE_NAME + ".appSetting";
    private static final String MAIN_SCREEN_MODEL_KEY = PACKAGE_NAME + ".mainScreenModel";
    private static final String DETAILS_SCREEN_MODEL_KEY = PACKAGE_NAME + ".detailsScreenModelKey";
    private static final String CITY_KEY = PACKAGE_NAME + ".cityKey";
    private final SharedPreferences sharedPreferences;
    private static SharedPreferencesManager instance;
    private final Gson gson;
    private final SharedPreferences.Editor editor;


    @SuppressLint("CommitPrefEdits")
    private SharedPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context.getApplicationContext());
        }
    }

    public static SharedPreferencesManager getInstance() {
        return instance;
    }

    public void putCity(City city) {
        String cityJson = gson.toJson(city);
        editor.putString(CITY_KEY, cityJson);
        editor.commit();
    }

    public City getCity() {
        String cityJson = sharedPreferences.getString(CITY_KEY, "");
        return gson.fromJson(cityJson, City.class);
    }

    public String getCityKey() {
        return sharedPreferences.getString(CITY_KEY, "");
    }

    public void putMainScreenModel(MainScreenModel mainScreenModel) {
        String mainScreenModelJson = gson.toJson(mainScreenModel);
        editor.putString(MAIN_SCREEN_MODEL_KEY, mainScreenModelJson);
        editor.commit();
    }

    public MainScreenModel getMainScreenModel() {
        String mainScreenModelJson = sharedPreferences.getString(MAIN_SCREEN_MODEL_KEY, "");
        return gson.fromJson(mainScreenModelJson, MainScreenModel.class);
    }

    public void putDetailsScreenModel(DetailsScreenModel detailsScreenModel) {
        String mainScreenModelJson = gson.toJson(detailsScreenModel);
        editor.putString(DETAILS_SCREEN_MODEL_KEY, mainScreenModelJson);
        editor.commit();
    }


    public DetailsScreenModel geDetailsScreenModel() {
        String detailsScreenModelJson = sharedPreferences.getString(DETAILS_SCREEN_MODEL_KEY, "");
        return gson.fromJson(detailsScreenModelJson, DetailsScreenModel.class);
    }
}
