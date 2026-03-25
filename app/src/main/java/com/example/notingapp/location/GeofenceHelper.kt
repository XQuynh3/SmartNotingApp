package com.example.notingapp.location

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.notingapp.model.Note
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingRequest

class GeofenceHelper(private val context: Context) {

    // 🔥 SINGLE GEOFENCE (FIX cho CreateNoteActivity)
    fun getGeofence(id: String, lat: Double, lng: Double): Geofence {
        return Geofence.Builder()
            .setRequestId(id)
            .setCircularRegion(lat, lng, 200f)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .build()
    }

    // 🔥 MULTIPLE GEOFENCE
    fun getGeofenceList(notes: List<Note>): List<Geofence> {
        return notes.mapNotNull { note ->

            val lat = note.latitude
            val lng = note.longitude

            if (lat != null && lng != null) {
                Geofence.Builder()
                    .setRequestId(note.id.toString())
                    .setCircularRegion(lat, lng, 200f)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
                    .setExpirationDuration(Geofence.NEVER_EXPIRE)
                    .build()
            } else null
        }
    }

    // 🔥 OVERLOAD cho cả 1 và nhiều geofence
    fun getGeofencingRequest(geofence: Geofence): GeofencingRequest {
        return GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(geofence)
            .build()
    }

    fun getGeofencingRequest(geofenceList: List<Geofence>): GeofencingRequest {
        return GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofences(geofenceList)
            .build()
    }

    // 🔥 KHÔNG truyền locationName nữa
    fun getPendingIntent(): PendingIntent {

        val intent = Intent(context, GeofenceReceiver::class.java)

        return PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
    }
}