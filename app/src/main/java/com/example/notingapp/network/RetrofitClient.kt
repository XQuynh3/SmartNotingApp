package com.example.notingapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.9:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}