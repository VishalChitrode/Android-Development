package com.example.googlesigninfirebase

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.googlesigninfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private val binding: ActivityMainBinding by lazy {
       ActivityMainBinding.inflate(layoutInflater)
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
        binding.signinFacebooklogin.setOnClickListener{
            Toast.makeText(this,"FaceBook",Toast.LENGTH_SHORT).show()
        }
    }
}