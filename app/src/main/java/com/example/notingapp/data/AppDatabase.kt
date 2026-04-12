package com.example.notingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notingapp.model.Note
import com.example.notingapp.model.Tag

@Database(
    entities = [Note::class, Tag::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun tagDao(): TagDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "note_db"
                )
                    .fallbackToDestructiveMigration() // QUAN TRỌNG
                    .build()
            }

            return INSTANCE!!
        }
    }
}