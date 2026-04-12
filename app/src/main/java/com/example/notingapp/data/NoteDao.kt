package com.example.notingapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notingapp.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note ORDER BY pinned DESC, lastModified DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM Note WHERE id=:id")
    suspend fun getNoteById(id: Int): Note

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("""
        SELECT * FROM Note 
        WHERE title LIKE '%' || :query || '%' 
        OR content LIKE '%' || :query || '%'
        ORDER BY pinned DESC, lastModified DESC
    """)
    fun search(query: String): LiveData<List<Note>>

    @Query("""
        SELECT * FROM Note 
        WHERE tag=:tag 
        ORDER BY pinned DESC, lastModified DESC
    """)
    fun filterTag(tag: String): LiveData<List<Note>>
}