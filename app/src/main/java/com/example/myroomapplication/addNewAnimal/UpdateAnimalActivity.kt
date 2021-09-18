package com.example.myroomapplication.addNewAnimal

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myroomapplication.AnimalsApplication
import com.example.myroomapplication.MainActivity
import com.example.myroomapplication.database.Animals
import com.example.myroomapplication.databinding.ActivityNewAnimalBinding

class UpdateAnimalActivity : AppCompatActivity() {
    private val updateViewModel: NewAnimalViewModel by viewModels {
        ((application as AnimalsApplication).viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val oldName = intent.getStringExtra(MainActivity.OLD_NAME)
        val oldAge = intent.getStringExtra(MainActivity.OLD_AGE)?.toInt()
        val oldBreed = intent.getStringExtra(MainActivity.OLD_BREED)
        val oldId = intent.getIntExtra(MainActivity.OLD_ID, 0)
        binding.addButton.text = "UPDATE"
        binding.addText.text = "Update chosen animal"
        binding.editName.setText("$oldName")
        binding.editAge.setText("$oldAge")
        binding.editBreed.setText("$oldBreed")
        binding.addButton.setOnClickListener {
            val name = binding.editName.text.toString()
            val age = binding.editAge.text.toString()
            val breed = binding.editBreed.text.toString()
            val animal = Animals(id = oldId, name = name, age = age, breed = breed)
            updateViewModel.update(animal)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
