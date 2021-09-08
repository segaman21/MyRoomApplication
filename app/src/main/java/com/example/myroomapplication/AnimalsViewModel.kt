package com.example.myroomapplication

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class AnimalsViewModel(private val repository: AnimalsRepository) : ViewModel() {
    val allAnimals: LiveData<List<Animals>> = repository.allAnimals.asLiveData()
    fun insert(animals: Animals) = viewModelScope.launch {
        repository.insert(animals)
    }
}

class AnimalsViewModelFactory(private val repository: AnimalsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AnimalsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}