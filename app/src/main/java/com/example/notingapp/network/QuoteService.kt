package com.example.notingapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuoteService {

    val api: QuoteApi by lazy {

        Retrofit.Builder()
            .baseUrl("https://api.quotable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuoteApi::class.java)
    }
}