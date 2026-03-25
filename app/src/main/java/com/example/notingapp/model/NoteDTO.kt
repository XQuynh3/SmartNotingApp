package com.example.notingapp.model

data class NoteDTO(
    val id: Int,
    val title: String,
    val content: String,
    val latitude: Double?,
    val longitude: Double?,
    val locationName: String?
)