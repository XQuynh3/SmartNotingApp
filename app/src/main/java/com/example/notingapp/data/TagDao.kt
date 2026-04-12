package com.example.notingapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notingapp.model.Tag

@Dao
interface TagDao {

    @Query("SELECT * FROM Tag ORDER BY name ASC")
    fun getTags(): LiveData<List<Tag>>

    @Insert
    suspend fun insert(tag: Tag)

    @Delete
    suspend fun delete(tag: Tag)

    @Query("SELECT COUNT(*) FROM Tag")
    suspend fun count(): Int
}