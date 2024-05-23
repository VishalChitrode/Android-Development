package com.example.androidarchitecturecomponents

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lifecycle.addObserver(Observer()) // Add observer only once in onCreate
        Log.d("Main", "Activity onCreate")
    }
    override fun onStart() {
        super.onStart()
        lifecycle.addObserver(Observer())
        Log.d("Main","Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        lifecycle.addObserver(Observer())
        Log.d("Main","Activity onResume")

    }

    override fun onPause() {
        super.onPause()
        lifecycle.addObserver(Observer())
        Log.d("Main","Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        lifecycle.addObserver(Observer())
        Log.d("Main","Activity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.addObserver(Observer())
        Log.d("Main","Activity onDestroy")
    }
}