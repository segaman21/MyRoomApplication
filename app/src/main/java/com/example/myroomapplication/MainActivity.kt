package com.example.myroomapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomapplication.database.Animals
import com.example.myroomapplication.preference.PreferenceActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var count = 1
    private val newAnimalRequestCode = 1
    private val animalsViewModel: AnimalsViewModel by viewModels {
        ((application as AnimalsApplication).viewModelFactory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)


        val button = findViewById<FloatingActionButton>(R.id.newAnimalButton)
        button.setOnClickListener {
            val intent = Intent(this, NewAnimalActivity::class.java)
            startActivityForResult(intent, newAnimalRequestCode)
        }

        animalsViewModel.allAnimals.observe(this, Observer {
            val adapter = AnimalsListAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

        })

        val deleteButton = findViewById<ImageView>(R.id.delete_button)
        deleteButton.setOnClickListener {
            animalsViewModel.delete()
        }

        val sortedButton = findViewById<ImageView>(R.id.sorted_button)
        sortedButton.setOnClickListener {
            val settingsIntent = Intent(this, PreferenceActivity::class.java)
            startActivity(settingsIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newAnimalRequestCode && resultCode == Activity.RESULT_OK) {
            var name = intentData?.getStringExtra(NewAnimalActivity.NAME)
            var age = intentData?.getStringExtra(NewAnimalActivity.AGE)
            var breed = intentData?.getStringExtra(NewAnimalActivity.BREED)
            val animal = Animals(count, name!!, age!!, breed!!)
            animalsViewModel.insert(animal)
            count++
        } else {
            Toast.makeText(applicationContext, "Aminals is empty", Toast.LENGTH_LONG).show()
        }
    }

}