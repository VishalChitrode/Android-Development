package com.example.sharedpreferences

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreferences.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private val binding : ActivityMain3Binding by lazy {
        ActivityMain3Binding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editor = getPreferences(MODE_PRIVATE)
        binding.mainActivity3Text.setText(editor.getString("name",null))
        val checkedValue = editor.getString("checked", null)
        binding.checkBox.isChecked = checkedValue?.toBoolean() ?: false



        binding.update.setOnClickListener {
            val editor = getPreferences(MODE_PRIVATE).edit()
            editor.putString("name", binding.mainActivity3Text.text.toString())
            editor.putBoolean("checked",binding.checkBox.isChecked)
            editor.apply()
        }
    }
}