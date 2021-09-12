package com.example.myroomapplication

import android.app.Application
import androidx.preference.PreferenceManager
import com.example.myroomapplication.database.AnimalsRoomDatabase
import com.example.myroomapplication.preference.SharedPreferenceStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AnimalsApplication : Application() {

    val viewModelFactory by lazy {AnimalsViewModelFactory(repository,sharedPreferences) }
    private val sharedPreferences by lazy {
        SharedPreferenceStorage(
            PreferenceManager.getDefaultSharedPreferences(
                this
            ),
        )
    }
    val database by lazy { AnimalsRoomDatabase.getDatabase(this) }
    val repository by lazy { AnimalsRepository(database.animalsDao()) }
}