package com.example.sharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import com.example.sharedpreferences.databinding.ActivityMainBinding
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key.equals("check_box_preference_1")){
                if (sharedPreferences.getBoolean("check_box_preference_1", false)) {
                    binding.main.setBackgroundColor(resources.getColor(R.color.black))
                } else {
                    binding.main.setBackgroundColor(resources.getColor(R.color.white))
                }
            }
        }
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        binding.email.setText(sharedPreferences.getString("email", ""))
        binding.password.setText(sharedPreferences.getString("password", ""))
        binding.login.setOnClickListener {
            editor = sharedPreferences.edit()
//            editor.putString("email", binding.email.text.toString())
//            editor.putString("password", binding.password.text.toString())
            val user = User(binding.email.text.toString(), binding.password.text.toString())
            val gson = Gson()
            val Data = gson.toJson(user,User::class.java)
            editor.putString("user", Data)

            editor.apply()
            startActivity(Intent(this, MainActivity2::class.java))
        }
        binding.setting.setOnClickListener {
            editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}
