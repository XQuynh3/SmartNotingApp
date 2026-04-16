package com.example.notingapp.model

import com.google.gson.annotations.SerializedName

data class NoteDTO(

    @SerializedName("_id")
    val id: String? = null,

    val title: String? = null,
    val content: String? = null,

    val ownerId: String,
    val sharedWith: List<String> = emptyList(),

    val updatedBy: String,
    val updatedAt: Long,

    val latitude: Double? = null,
    val longitude: Double? = null,
    val locationName: String? = null
)