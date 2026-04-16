package com.example.notingapp.repository

import android.content.Context
import android.util.Log
import com.example.notingapp.model.Note
import com.example.notingapp.model.NoteDTO
import com.example.notingapp.model.ShareRequestDTO
import com.example.notingapp.network.RetrofitClient

class NoteRepository(private val context: Context) {

    private val api = RetrofitClient.api
    private val userId = "userA"

    suspend fun syncFromServer(): Pair<List<Note>, Int> {
        return try {

            val prefs = context.getSharedPreferences("sync", Context.MODE_PRIVATE)
            val lastSync = prefs.getLong("last_sync", 0)

            val remoteNotes = api.getNotes(userId)

            val newNotes = remoteNotes.filter {
                it.updatedAt > lastSync
            }

            val mapped = remoteNotes.map {
                Note(
                    title = it.title ?: "",
                    content = it.content ?: "",
                    tag = if (it.ownerId == userId) "Mine" else "Shared",
                    latitude = it.latitude,
                    longitude = it.longitude,
                    locationName = it.locationName,
                    lastModified = it.updatedAt
                )
            }

            prefs.edit().putLong("last_sync", System.currentTimeMillis()).apply()

            Pair(mapped, newNotes.size)

        } catch (e: Exception) {
            Log.e("API", "SYNC FAILED: ${e.message}")
            Pair(emptyList(), 0) // 🔥 tránh crash
        }
    }

    suspend fun pushToServer(note: Note) {
        try {
            val dto = NoteDTO(
                title = note.title.ifEmpty { "No title" },
                content = note.content.ifEmpty { "No content" },
                ownerId = userId,
                updatedBy = userId,
                updatedAt = System.currentTimeMillis(),
                latitude = note.latitude,
                longitude = note.longitude,
                locationName = note.locationName
            )

            api.createNote(dto)

        } catch (e: Exception) {
            Log.e("API", "Push FAILED: ${e.message}")
        }
    }

    // 🔥 SEND REQUEST
    suspend fun sendShareRequest(noteId: String, targetUserId: String) {
        try {
            api.sendShareRequest(
                ShareRequestDTO(
                    noteId = noteId,
                    fromUserId = userId,
                    toUserId = targetUserId
                )
            )
        } catch (e: Exception) {
            Log.e("API", "Send request failed: ${e.message}")
        }
    }

    // 🔥 GET REQUESTS
    suspend fun getRequests(): List<ShareRequestDTO> {
        return try {
            api.getRequests(userId)
        } catch (e: Exception) {
            Log.e("API", "Get requests failed: ${e.message}")
            emptyList()
        }
    }

    suspend fun acceptRequest(id: String) {
        try {
            api.acceptRequest(mapOf("requestId" to id))
        } catch (e: Exception) {
            Log.e("API", "Accept failed: ${e.message}")
        }
    }

    suspend fun rejectRequest(id: String) {
        try {
            api.rejectRequest(mapOf("requestId" to id))
        } catch (e: Exception) {
            Log.e("API", "Reject failed: ${e.message}")
        }
    }

}