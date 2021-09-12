package com.example.myroomapplication

import com.example.myroomapplication.database.Animals
import com.example.myroomapplication.database.AnimalsDao
import kotlinx.coroutines.flow.Flow

class AnimalsRepository(private val animalsDao: AnimalsDao) {

    val allAnimals: Flow<List<Animals>> = animalsDao.getAllWords()

    suspend fun delete(){
        animalsDao.deleteAll()
    }

    suspend fun insert (animals: Animals){
        animalsDao.insert(animals)
    }
}