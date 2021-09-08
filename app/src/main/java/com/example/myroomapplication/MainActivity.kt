package com.example.myroomapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var count = 0
    private val newAnimalRequestCode = 1
    private val animalsViewModel: AnimalsViewModel by viewModels {
        AnimalsViewModelFactory((application as AnimalsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = AnimalsListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val button=findViewById<FloatingActionButton>(R.id.newAnimalButton)
        button.setOnClickListener {
            val intent=Intent(this,NewAnimalActivity::class.java)
            startActivityForResult(intent,newAnimalRequestCode)
        }

        animalsViewModel.allAnimals.observe(this, Observer { animals ->
            animals.let { adapter.submitList(it) }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newAnimalRequestCode && resultCode == Activity.RESULT_OK) {
            var name=intentData?.getStringExtra(NewAnimalActivity.NAME)
            var age=intentData?.getStringExtra(NewAnimalActivity.AGE)
            var breed=intentData?.getStringExtra(NewAnimalActivity.BREED)
            val animal = Animals(count, name!!,age!!,breed!!)
            animalsViewModel.insert(animal)
            count++
            }
        else {
            Toast.makeText(applicationContext, "Aminals is empty", Toast.LENGTH_LONG).show()
        }
    }

}