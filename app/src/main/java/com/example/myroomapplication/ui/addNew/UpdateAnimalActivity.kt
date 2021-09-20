package com.example.myroomapplication.ui.addNew

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myroomapplication.Aap
import com.example.myroomapplication.MainActivity
import com.example.myroomapplication.R
import com.example.myroomapplication.data.Animal
import com.example.myroomapplication.databinding.ActivityNewAnimalBinding

class UpdateAnimalActivity : AppCompatActivity() {
    private val updateViewModel: NewAnimalViewModel by viewModels {
        ((application as Aap).viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewAnimalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val oldName = intent.getStringExtra(OLD_NAME)
        val oldAge = intent.getStringExtra(OLD_AGE)?.toInt()
        val oldBreed = intent.getStringExtra(OLD_BREED)
        val oldId = intent.getIntExtra(OLD_ID, 0)
        binding.addButton.setText (R.string.update)
        binding.addText.setText (R.string.update_chosen)
        binding.editName.setText("$oldName")
        binding.editAge.setText("$oldAge")
        binding.editBreed.setText("$oldBreed")
        binding.addButton.setOnClickListener {
            val name = binding.editName.text.toString()
            val age = binding.editAge.text.toString()
            val breed = binding.editBreed.text.toString()
            val animal = Animal(id = oldId, name = name, age = age, breed = breed)
            updateViewModel.update(animal)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val OLD_ID = "ID"
        private const val OLD_NAME = "NAME"
        private const val OLD_AGE = "AGE"
        private const val OLD_BREED = "BREED"

        fun createIntent(context: Context, animal: Animal) =
            Intent(context, UpdateAnimalActivity::class.java).apply {
                putExtra(OLD_ID, animal.id)
                putExtra(OLD_NAME, animal.name)
                putExtra(OLD_AGE, animal.age)
                putExtra(OLD_BREED, animal.breed)
            }
    }
}
