package com.example.a26navigatefragmentsusingnavigationgraph.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.a26navigatefragmentsusingnavigationgraph.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}