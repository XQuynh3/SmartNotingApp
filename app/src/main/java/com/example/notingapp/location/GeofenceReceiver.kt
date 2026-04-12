package com.example.notingapp.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.notingapp.data.AppDatabase
import com.google.android.gms.location.GeofencingEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GeofenceReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val event = GeofencingEvent.fromIntent(intent) ?: return
        if (event.hasError()) return

        val triggeringGeofences = event.triggeringGeofences ?: return // 🔥 FIX

        val db = AppDatabase.getDatabase(context)
        val noteDao = db.noteDao()

        for (geofence in triggeringGeofences) {

            val noteId = geofence.requestId.toIntOrNull() ?: continue // 🔥 SAFE

            CoroutineScope(Dispatchers.IO).launch {

                val note = noteDao.getNoteById(noteId)

                val locationName = note?.locationName ?: "this place"

                NotificationHelper.showNotification(
                    context,
                    "Location Reminder",
                    "You have a note at $locationName"
                )
            }
        }
    }
}