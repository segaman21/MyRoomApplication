package com.example.myroomapplication.data

import kotlinx.coroutines.flow.Flow

class AnimalsRepository(private val animalsDao: AnimalsDao) {

    var allAnimals: Flow<List<Animal>> = animalsDao.getAllAnimals()

    suspend fun insert (animal: Animal){
        animalsDao.insert(animal)
    }

    suspend fun deleteChosen(id:Int){
        animalsDao.deleteChosen(id)
    }

    suspend fun update(animal: Animal){
        animalsDao.update(animal.id,animal.name,animal.age,animal.breed)
    }
}