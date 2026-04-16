package com.example.notingapp.model

data class ShareRequestDTO(
    val _id: String? = null,
    val noteId: String,
    val fromUserId: String,
    val toUserId: String,
    val status: String? = null
)