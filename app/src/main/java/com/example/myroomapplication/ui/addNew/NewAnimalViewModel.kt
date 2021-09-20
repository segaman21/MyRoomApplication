package com.example.myroomapplication.ui.addNew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myroomapplication.data.AnimalsRepository
import com.example.myroomapplication.data.Animal
import kotlinx.coroutines.launch

class NewAnimalViewModel(private val repository: AnimalsRepository) : ViewModel() {

    fun insert(animal: Animal) = viewModelScope.launch {
        repository.insert(animal)
    }

    fun update(animal:Animal) = viewModelScope.launch {
        repository.update(animal)
    }
}

