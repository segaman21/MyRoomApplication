package com.example.myroomapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewAnimalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_animal)
        val editName = findViewById<EditText>(R.id.edit_name)
        val editAge = findViewById<EditText>(R.id.edit_age)
        val editBreed = findViewById<EditText>(R.id.edit_breed)
        val button = findViewById<Button>(R.id.add_button)
        button.setOnClickListener {
            val addIntent = Intent()
            if (TextUtils.isEmpty(editName.text) ||
                TextUtils.isEmpty(editAge.text) ||
                TextUtils.isEmpty(editBreed.text)
            ) setResult(Activity.RESULT_CANCELED, addIntent)
            else {
                val name = editName.text.toString()
                val age = editAge.text.toString()
                val breed = editBreed.text.toString()
                addIntent.putExtra(NAME,name)
                addIntent.putExtra(AGE,age)
                addIntent.putExtra(BREED,breed)
                setResult(Activity.RESULT_OK, addIntent)
            }
            finish()
        }
    }
    companion object {
        const val NAME = "ANIMALS_NAME"
        const val AGE = "ANIMALS_AGE"
        const val BREED = "ANIMALS_BREED"
    }
}