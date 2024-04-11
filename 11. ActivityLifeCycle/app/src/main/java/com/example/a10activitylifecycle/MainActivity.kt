package com.example.a10activitylifecycle

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState) // when app open this method will run
        Log.d("TAG","onCreated Created")
        Toast.makeText(this, "onCreated Called", Toast.LENGTH_LONG).show()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets



        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG","onStart Created")
        Toast.makeText(this, "onStart Called", Toast.LENGTH_LONG).show()

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG","OnRestart Created")

        Toast.makeText(this, "onRestart Called", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
       Log.d("TAG","onResume Created")
        Toast.makeText(this, "onResume Called", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG","onPause Created")
        Toast.makeText(this, "onPause Called", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG","onDestroy Created")
        Toast.makeText(this, "onDestroy Called", Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG","onStop Called")
        Toast.makeText(this, "onStop Called", Toast.LENGTH_LONG).show()
    }

}