package com.example.myroomapplication.preference

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.myroomapplication.R

class PreferenceSettings:PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
       return
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)
    }
}