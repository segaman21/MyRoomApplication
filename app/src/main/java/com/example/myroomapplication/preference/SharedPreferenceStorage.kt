package com.example.myroomapplication.preference

import android.content.SharedPreferences
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

class SharedPreferenceStorage(private val sharedPreference: SharedPreferences) : PreferenceStorage {
    private var onPreferencesChanged = callbackFlow {
        send(Unit)
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
            trySend(Unit)
        }
        sharedPreference.registerOnSharedPreferenceChangeListener(listener)
        awaitClose {
            sharedPreference.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    override var observableAnimalOrderBy: Flow<String?> = onPreferencesChanged.map {
        sharedPreference.getString(PreferenceStorage.Keys.LIST_ORDER_BY, null)
    }.distinctUntilChanged()

}