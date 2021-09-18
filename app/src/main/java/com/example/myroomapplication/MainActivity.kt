package com.example.myroomapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroomapplication.addNewAnimal.NewAnimalActivity
import com.example.myroomapplication.addNewAnimal.UpdateAnimalActivity
import com.example.myroomapplication.database.Animals
import com.example.myroomapplication.databinding.ActivityMainBinding
import com.example.myroomapplication.preference.PreferenceActivity


class MainActivity() : AppCompatActivity(), AnimalsListener {
    private val animalsViewModel: AnimalsViewModel by viewModels {
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
        animalsViewModel.allAnimals.observe(this, Observer { animals ->
            animals.let { animalAdapter.submitList(it) }
        })

        binding.sortedButton.setOnClickListener {
            val settingsIntent = Intent(this, PreferenceActivity::class.java)
            startActivity(settingsIntent)
        }
    }

    override fun delete(id: Int) {
        animalsViewModel.deleteChosen(id)
    }

    override fun update(animals: Animals) {
        val intent = Intent(this, UpdateAnimalActivity::class.java)
        intent.putExtra(OLD_ID, animals.id)
        intent.putExtra(OLD_NAME, animals.name)
        intent.putExtra(OLD_AGE, animals.age)
        intent.putExtra(OLD_BREED, animals.breed)
        startActivity(intent)
    }

    companion object {
        const val OLD_ID = "ID"
        const val OLD_NAME = "NAME"
        const val OLD_AGE = "AGE"
        const val OLD_BREED = "BREED"
    }
}