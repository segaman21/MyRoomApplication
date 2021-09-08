package com.example.myroomapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Animals::class], version = 1)
abstract class AnimalsRoomDatabase : RoomDatabase() {

    abstract fun animalsDao(): AnimalsDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalsRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AnimalsRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalsRoomDatabase::class.java,
                    "animals_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.animalsDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(animalDao: AnimalsDao) {
            animalDao.deleteAll()
          }
    }
}