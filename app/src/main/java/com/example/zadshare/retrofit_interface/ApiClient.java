package com.example.zadshare.retrofit_interface;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://zadshareapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
