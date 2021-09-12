package com.example.myroomapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroomapplication.addNewAnimal.NewAnimalActivity
import com.example.myroomapplication.databinding.ActivityMainBinding
import com.example.myroomapplication.preference.PreferenceActivity

class MainActivity : AppCompatActivity() {
    private val animalsViewModel: AnimalsViewModel by viewModels {
        ((application as AnimalsApplication).viewModelFactory)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = AnimalsListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.newAnimalButton.setOnClickListener {
            val intent = Intent(this, NewAnimalActivity::class.java)
            startActivity(intent)
        }
        animalsViewModel.allAnimals.observe(this, Observer { animals ->
            animals.let { adapter.submitList(it) }
        })
        binding.deleteButton.setOnClickListener {
            animalsViewModel.delete()
        }
        binding.sortedButton.setOnClickListener {
            val settingsIntent = Intent(this, PreferenceActivity::class.java)
            startActivity(settingsIntent)
        }
    }
}