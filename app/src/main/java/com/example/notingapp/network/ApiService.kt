package com.example.notingapp.network

import com.example.notingapp.model.NoteDTO
import retrofit2.http.*

interface ApiService {

    @POST("notes")
    suspend fun createNote(@Body note: NoteDTO)

    @GET("notes")
    suspend fun getNotes(): List<NoteDTO>

    @DELETE("notes/{id}")
    suspend fun deleteNote(@Path("id") id: Int)
}