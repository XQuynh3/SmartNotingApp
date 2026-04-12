package com.example.notingapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.notingapp.data.AppDatabase
import com.example.notingapp.model.Tag
import kotlinx.coroutines.launch

class TagViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).tagDao()

    val tags: LiveData<List<Tag>> = dao.getTags()

    fun insert(tag: Tag) = viewModelScope.launch {
        dao.insert(tag)
    }

    fun delete(tag: Tag) = viewModelScope.launch {
        dao.delete(tag)
    }
}