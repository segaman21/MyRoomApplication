package com.example.myroomapplication

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AnimalsApplication:Application() {
    val applicationScope= CoroutineScope(SupervisorJob())

    val database by lazy {AnimalsRoomDatabase.getDatabase(this,applicationScope)}
    val repository by lazy {AnimalsRepository(database.animalsDao())}
}