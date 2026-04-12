package com.example.notingapp.network

import retrofit2.http.GET

data class Quote(
    val content: String,
    val author: String
)

interface QuoteApi {

    @GET("random")
    suspend fun getRandomQuote(): Quote
}