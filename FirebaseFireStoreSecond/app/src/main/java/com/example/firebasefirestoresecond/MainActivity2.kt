package com.example.firebasefirestoresecond

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasefirestoresecond.databinding.ActivityMain2Binding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity2 : AppCompatActivity() {
    private val binding : ActivityMain2Binding by lazy {
        ActivityMain2Binding.inflate(layoutInflater)
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
        val db = Firebase.firestore
        binding.updateName.setText(intent.getStringExtra("name"))
        binding.updatePassword.setText(intent.getStringExtra("password"))
        binding.updateButton.setOnClickListener {
            val user = hashMapOf(
                "name" to binding.updateName.text.toString(),
                "password" to binding.updatePassword.text.toString(),

            )
            db.collection("users").document(intent.getStringExtra("id")!! ).set(user)
            finish()
        }
    }
}