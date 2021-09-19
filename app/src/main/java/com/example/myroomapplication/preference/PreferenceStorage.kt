package com.example.myroomapplication.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    object Keys{
        const val LIST_ORDER_BY= "sorted"
    }
    val observableAnimalOrderBy: Flow<String?>
}