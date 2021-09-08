package com.example.myroomapplication

import kotlinx.coroutines.flow.Flow

class AnimalsRepository(private val animalsDao: AnimalsDao) {

    val allAnimals: Flow<List<Animals>> = animalsDao.getAllWords()

    suspend fun insert (animals:Animals){
        animalsDao.insert(animals)
    }

}