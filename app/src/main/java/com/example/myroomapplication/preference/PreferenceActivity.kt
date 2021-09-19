package com.example.myroomapplication.preference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(
            android.R.id.content, PreferenceSettings()
        ).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}