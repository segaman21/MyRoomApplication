package com.example.myroomapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroomapplication.addNewAnimal.NewAnimalActivity
import com.example.myroomapplication.database.Animals
import com.example.myroomapplication.databinding.ActivityMainBinding
import com.example.myroomapplication.preference.PreferenceActivity
import kotlinx.coroutines.Job

class MainActivity() : AppCompatActivity() {
    private val animalsViewModel: AnimalsViewModel by viewModels {
        ((application as AnimalsApplication).viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animalAdapter = AnimalsListAdapter{animal -> adapterOnClick(animal)}
        binding.recyclerview.adapter = animalAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.newAnimalButton.setOnClickListener {
            val intent = Intent(this, NewAnimalActivity::class.java)
            startActivity(intent)
        }
        animalsViewModel.allAnimals.observe(this, Observer { animals ->
            animals.let { animalAdapter.submitList(it) }
        })

        binding.sortedButton.setOnClickListener {
            val settingsIntent = Intent(this, PreferenceActivity::class.java)
            startActivity(settingsIntent)
        }
    }
    private fun adapterOnClick(animal: Animals) {
        animalsViewModel.deleteChosen(animal.id)
    }


}