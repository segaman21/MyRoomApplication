package com.example.myroomapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Animals::class], version = 3)
abstract class AnimalsRoomDatabase : RoomDatabase() {

    abstract fun animalsDao(): AnimalsDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalsRoomDatabase? = null

        fun getDatabase(context: Context): AnimalsRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalsRoomDatabase::class.java,
                    "animals_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}