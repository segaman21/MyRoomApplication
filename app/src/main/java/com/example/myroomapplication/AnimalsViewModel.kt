package com.example.myroomapplication

import androidx.lifecycle.*
import com.example.myroomapplication.database.Animals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AnimalsViewModel(private val repository: AnimalsRepository) : ViewModel() {

    private val scope= CoroutineScope(SupervisorJob())

    val allAnimals: LiveData<List<Animals>> = repository.allAnimals.asLiveData()

    val allByName: LiveData<List<Animals>> = repository.allByName.asLiveData()
    val allByAge: LiveData<List<Animals>> = repository.allByAge.asLiveData()
    val allByBreed: LiveData<List<Animals>> = repository.allByBreed.asLiveData()

    fun insert(animals: Animals) = viewModelScope.launch {
        repository.insert(animals)
    }

    fun delete() = scope.launch(Dispatchers.IO) {
        repository.delete()
    }
}

class AnimalsViewModelFactory(private val repository: AnimalsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimalsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}