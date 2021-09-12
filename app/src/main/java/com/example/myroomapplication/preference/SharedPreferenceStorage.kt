package com.example.myroomapplication.preference

import android.content.SharedPreferences
import com.example.myroomapplication.database.Animals
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

class SharedPreferenceStorage(private val sharedPreference: SharedPreferences) : PreferenceStorage {
    private val onPreferencesChanged = callbackFlow {
        send(Unit)
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
            trySend(Unit)
        }
        sharedPreference.registerOnSharedPreferenceChangeListener(listener)
        awaitClose {
            sharedPreference.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    override val observableAnimalOrderBy: Flow<String?> = onPreferencesChanged.map {
        sharedPreference.getString(PreferenceStorage.Keys.LIST_ORDER_BY, null)
    }.distinctUntilChanged()

}