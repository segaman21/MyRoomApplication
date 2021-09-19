package com.example.myroomapplication

import android.util.Log
import androidx.lifecycle.*
import com.example.myroomapplication.addNewAnimal.NewAnimalViewModel
import com.example.myroomapplication.database.Animal
import com.example.myroomapplication.preference.PreferenceStorage
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.combine

class AllAnimalsViewModel(
    private val repository: AnimalsRepository,
    preferenceStorage: PreferenceStorage,
) : ViewModel() {

    val allAnimals: LiveData<List<Animal>> = combine(
        repository.allAnimals, preferenceStorage.observableAnimalOrderBy
    ) { allAnimals, sorted ->
        Log.d("tag", "$sorted")
        when (sorted) {
            "name" -> allAnimals.sortedBy { it.name }
            "age" -> allAnimals.sortedBy { it.age.toInt() }
            "breed" -> allAnimals.sortedBy { it.breed }
            else -> allAnimals
        }
    }.asLiveData()

    fun deleteChosen(id: Int) = viewModelScope.launch {
        repository.deleteChosen(id)
    }

}

class AnimalsViewModelFactory(
    private val repository: AnimalsRepository, private val preferenceStorage: PreferenceStorage
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllAnimalsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return AllAnimalsViewModel(
                repository,
                preferenceStorage
            ) as T
        }
        if (modelClass.isAssignableFrom(NewAnimalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return NewAnimalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}