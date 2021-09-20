package com.example.myroomapplication.ui.addNew

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import com.example.myroomapplication.Aap
import com.example.myroomapplication.MainActivity
import com.example.myroomapplication.R
import com.example.myroomapplication.data.Animal
import com.example.myroomapplication.databinding.ActivityNewAnimalBinding

class NewAnimalActivity : AppCompatActivity() {
    private val newAnimalViewModel: NewAnimalViewModel by viewModels {
        ((application as Aap).viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.editName.text) ||
                TextUtils.isEmpty(binding.editAge.text) ||
                TextUtils.isEmpty(binding.editBreed.text)
            ) {
                Toast.makeText(applicationContext, R.string.empty_animal, Toast.LENGTH_LONG).show()
            } else {
                val name = binding.editName.text.toString()
                val age = binding.editAge.text.toString()
                val breed = binding.editBreed.text.toString()
                val animal = Animal(name = name, age = age, breed = breed)
                newAnimalViewModel.insert(animal)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}