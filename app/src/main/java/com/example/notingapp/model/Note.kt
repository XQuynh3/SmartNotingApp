package com.example.notingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var title: String,
    var content: String,
    var tag: String,

    var color: Int = 0xFFFFFFFF.toInt(),
    var pinned: Boolean = false,
    var textSize: Float = 16f,

    var bold: Boolean = false,
    var italic: Boolean = false,

    var lastModified: Long = System.currentTimeMillis(),

    // 🔥 LOCATION
    var latitude: Double? = null,
    var longitude: Double? = null,
    var locationName: String? = null
)