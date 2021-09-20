package com.example.myroomapplication

import android.app.Application
import androidx.preference.PreferenceManager
import com.example.myroomapplication.data.AnimalsRepository
import com.example.myroomapplication.data.AnimalsRoomDatabase
import com.example.myroomapplication.preference.SharedPreferenceStorage
import com.example.myroomapplication.ui.animalsList.AnimalsViewModelFactory

class App : Application() {

    val viewModelFactory by lazy { AnimalsViewModelFactory(repository, sharedPreferences) }
    private val sharedPreferences by lazy {
        SharedPreferenceStorage(
            PreferenceManager.getDefaultSharedPreferences(
                this
            ),
        )
    }
    private val database by lazy { AnimalsRoomDatabase.getDatabase(this) }
    private val repository by lazy { AnimalsRepository(database.animalsDao()) }
}