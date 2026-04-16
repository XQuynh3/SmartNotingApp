package com.example.notingapp.network

import com.example.notingapp.model.NoteDTO
import com.example.notingapp.model.ShareRequestDTO
import retrofit2.http.*

interface ApiService {

    @POST("notes")
    suspend fun createNote(@Body note: NoteDTO)

    @GET("notes")
    suspend fun getNotes(
        @Query("userId") userId: String
    ): List<NoteDTO>

    // 🔥 CREATE REQUEST
    @POST("share-request")
    suspend fun sendShareRequest(@Body request: ShareRequestDTO)

    // 🔥 GET REQUESTS
    @GET("share-request")
    suspend fun getRequests(
        @Query("userId") userId: String
    ): List<ShareRequestDTO>

    // 🔥 ACCEPT
    @POST("share-request/accept")
    suspend fun acceptRequest(@Body body: Map<String, String>)

    // 🔥 REJECT
    @POST("share-request/reject")
    suspend fun rejectRequest(@Body body: Map<String, String>)
}