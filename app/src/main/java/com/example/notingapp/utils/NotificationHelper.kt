package com.example.notingapp.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.notingapp.MainActivity

object NotificationHelper {

    private const val CHANNEL_ID = "sync_channel"

    fun showNotification(context: Context, count: Int, noteId: String?) {

        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 🔥 CREATE CHANNEL
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Sync Updates",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        // 🔥 INTENT mở app
        val intent = Intent(context, MainActivity::class.java)

        // 🔥 THÊM Ở ĐÂY
        if (noteId != null) {
            intent.putExtra("noteId", noteId)
        }

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Notes Updated")
            .setContentText("$count new updates from shared notes")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        manager.notify(1, notification)
    }
}