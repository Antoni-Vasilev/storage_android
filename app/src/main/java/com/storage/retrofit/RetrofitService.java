package com.storage.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.100.11:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
