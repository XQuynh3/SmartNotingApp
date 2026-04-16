package com.example.notingapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.notingapp.data.AppDatabase
import com.example.notingapp.model.Note
import com.example.notingapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).noteDao()

    // 🔥 FIX: truyền context vào repo
    private val repo = NoteRepository(application)

    val notes = dao.getAllNotes()

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(note)

            // 🔥 PUSH TO SERVER
            repo.pushToServer(note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            note.lastModified = System.currentTimeMillis()
            dao.update(note)

            // 👉 (optional) nếu muốn sync update luôn
            repo.pushToServer(note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(note)
        }
    }

    fun syncFromServer() {
        viewModelScope.launch(Dispatchers.IO) {

            // 🔥 FIX: destructuring Pair
            val (remoteNotes, _) = repo.syncFromServer()

            remoteNotes.forEach { note ->
                dao.insert(note)
            }
        }
    }

    fun search(query: String) = dao.search(query)

    fun filterTag(tag: String) = dao.filterTag(tag)
}