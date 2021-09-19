package com.example.myroomapplication.addNewAnimal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroomapplication.AnimalsRepository
import com.example.myroomapplication.database.Animal
import kotlinx.coroutines.launch

class NewAnimalViewModel(private val repository: AnimalsRepository) : ViewModel() {

    fun insert(animal: Animal) = viewModelScope.launch {
        repository.insert(animal)
    }

    fun update(animal:Animal) = viewModelScope.launch {
        repository.update(animal)
    }
}

