package com.example.notingapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.notingapp.data.AppDatabase
import com.example.notingapp.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).noteDao()

    val notes = dao.getAllNotes()

    fun insert(note: Note) {

        viewModelScope.launch {
            dao.insert(note)
        }
    }

    fun update(note: Note) {

        viewModelScope.launch {
            note.lastModified = System.currentTimeMillis()
            dao.update(note)
        }
    }

    fun delete(note: Note) {

        viewModelScope.launch {
            dao.delete(note)
        }
    }

    fun search(query: String) = dao.search(query)

    fun filterTag(tag: String) = dao.filterTag(tag)
}