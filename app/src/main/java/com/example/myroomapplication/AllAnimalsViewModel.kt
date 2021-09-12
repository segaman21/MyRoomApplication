package com.example.myroomapplication

import android.util.Log
import androidx.lifecycle.*
import com.example.myroomapplication.addNewAnimal.NewAnimalViewModel
import com.example.myroomapplication.database.Animals
import com.example.myroomapplication.preference.PreferenceStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class AnimalsViewModel(
    private val repository: AnimalsRepository,
    preferenceStorage: PreferenceStorage
) : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob())

    val allAnimals: LiveData<List<Animals>> =
        combine(
            repository.allAnimals, preferenceStorage.observableAnimalOrderBy
        ) { allAnimals, sorted ->
            Log.d("tag", "$sorted")
            when (sorted) {
                "name" -> allAnimals.sortedBy { it.name }
                "age" ->allAnimals.sortedBy { it.age.toInt() }
                "breed" ->allAnimals.sortedBy { it.breed }
                else->allAnimals
            }
        }.asLiveData()


    fun delete() = scope.launch(Dispatchers.IO) {
        repository.delete()
    }
}

class AnimalsViewModelFactory(
    private val repository: AnimalsRepository,
    private val preferenceStorage: PreferenceStorage
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimalsViewModel(repository, preferenceStorage) as T
        }
        if (modelClass.isAssignableFrom(NewAnimalViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return NewAnimalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}