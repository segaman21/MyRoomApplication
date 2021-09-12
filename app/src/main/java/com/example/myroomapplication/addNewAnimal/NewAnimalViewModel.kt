package com.example.myroomapplication.addNewAnimal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myroomapplication.AnimalsRepository
import com.example.myroomapplication.database.Animals
import kotlinx.coroutines.launch

class NewAnimalViewModel(private val repository: AnimalsRepository) : ViewModel() {

    fun insert(animals: Animals) = viewModelScope.launch {
        repository.insert(animals)
    }
}

