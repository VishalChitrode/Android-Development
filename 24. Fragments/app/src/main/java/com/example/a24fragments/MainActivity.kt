package com.example.a24fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a24fragments.databinding.ActivityMainBinding
import com.example.a24fragments.fragment.BlankFragment1
import com.example.a24fragments.fragment.BlankFragment2


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {

      ActivityMainBinding.inflate(layoutInflater)
    }



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        var manager = supportFragmentManager // Take Fragment manager support
//        var tr = manager.beginTransaction() // begin transition
//        tr.replace(R.id.main,BlankFragment1()) // R.id.main is the ID of the container where the fragment will be placed
//        tr.addToBackStack(null) // // You can also add the transaction to the backstack if you want to navigate back to the previous fragment when pressing the back button
//        tr.commit() // // Commit the transaction


        var manager = supportFragmentManager // Take Fragment manager support
        var tr = manager.beginTransaction() // begin transition
        tr.replace(R.id.frame,BlankFragment1()) // R.id.main is the ID of the container where the fragment will be placed
        tr.addToBackStack(null) // // You can also add the transaction to the backstack if you want to navigate back to the previous fragment when pressing the back button
        tr.commit() // // Commit the transaction

        binding.button1.setOnClickListener {
            var manager = supportFragmentManager // Take Fragment manager support
            var tr = manager.beginTransaction() // begin transition
            tr.replace(R.id.frame,BlankFragment1()) // R.id.main is the ID of the container where the fragment will be placed
            tr.addToBackStack(null) // // You can also add the transaction to the backstack if you want to navigate back to the previous fragment when pressing the back button
            tr.commit() // // Commit the transaction

        }
        binding.button2.setOnClickListener {
            var manager = supportFragmentManager // Take Fragment manager support
            var tr = manager.beginTransaction() // begin transition
            tr.replace(R.id.frame, BlankFragment2()) // R.id.main is the ID of the container where the fragment will be placed
            tr.addToBackStack(null) // // You can also add the transaction to the backstack if you want to navigate back to the previous fragment when pressing the back button
            tr.commit() // // Commit the transaction
        }


    }
}