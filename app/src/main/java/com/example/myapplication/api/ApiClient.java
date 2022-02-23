package com.example.myapplication.api;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://nagarnigamprojects.in/mbbook/mobile_webservice/";
    public static ApiInterface getClient() {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit r = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        return r.create(ApiInterface.class);
    }
}
