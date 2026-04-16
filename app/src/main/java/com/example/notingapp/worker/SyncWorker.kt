package com.example.notingapp.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.ListenableWorker.Result
import com.example.notingapp.repository.NoteRepository
import com.example.notingapp.utils.NotificationHelper

class SyncWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {

            val repo = NoteRepository(applicationContext)

            val (notes, newCount) = repo.syncFromServer()

            // 🔥 SHOW NOTIFICATION
            if (newCount > 0) {

                // 🔥 LẤY NOTE MỚI NHẤT
                val latestNote = notes.maxByOrNull { it.lastModified }

                val noteId = latestNote?.id?.toString()

                NotificationHelper.showNotification(
                    applicationContext,
                    newCount,
                    noteId
                )
            }

            Result.success()

        } catch (_: Exception) {
            Result.retry()
        }
    }
}