package com.example.myroomapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroomapplication.addNewAnimal.NewAnimalActivity
import com.example.myroomapplication.addNewAnimal.UpdateAnimalActivity
import com.example.myroomapplication.database.Animal
import com.example.myroomapplication.databinding.ActivityMainBinding
import com.example.myroomapplication.preference.PreferenceActivity


class MainActivity() : AppCompatActivity(), AnimalsListener {
    private val allAnimalsViewModel: AllAnimalsViewModel by viewModels {
        ((application as AnimalsApplication).viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animalAdapter = AnimalsListAdapter(this)
        binding.recyclerview.adapter = animalAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.newAnimalButton.setOnClickListener {
            val intent = Intent(this, NewAnimalActivity::class.java)
            startActivity(intent)
        }
        allAnimalsViewModel.allAnimals.observe(this, Observer { animals ->
            animals.let { animalAdapter.submitList(it) }
        })

        binding.sortedButton.setOnClickListener {
            val settingsIntent = Intent(this, PreferenceActivity::class.java)
            startActivity(settingsIntent)
        }
    }

    override fun delete(id: Int) {
        allAnimalsViewModel.deleteChosen(id)
    }

    override fun update(animal: Animal) {
        startActivity(UpdateAnimalActivity.createIntent(this, animal))
    }

}