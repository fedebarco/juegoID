package com.example.juegoid.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.juegoid.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}