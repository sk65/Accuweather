package com.example.accuweather_2_0.network;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String LANGUAGE_RU = "ru";
    public static final String BASE_URL = "http://dataservice.accuweather.com/";
    public static final String API_KEY = "03QO2CXrFNs2IDfGv71e3bPP6uiB6OL8";

    private static Retrofit instance;
    private static OkHttpClient okHttpClient;

    public static Retrofit getInstance() {
        if (instance == null) {
            buildOkHttpClient();
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return instance;
    }

    private static void buildOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    HttpUrl newUrl = originalRequest.url().newBuilder()
                            .addQueryParameter("apikey", API_KEY)
                            .addQueryParameter("language", LANGUAGE_RU)
                            .build();
                    Request.Builder builder = originalRequest.newBuilder().url(newUrl);
                    return chain.proceed(builder.build());
                })
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
