package com.example.a24fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a24fragments.databinding.ActivityMainBinding
import com.example.a24fragments.databinding.ActivityMainBinding.inflate
import com.example.a24fragments.databinding.FragmentBlank1Binding
import com.example.a24fragments.fragment.BlankFragment1
import com.example.a24fragments.fragment.BlankFragment2

class MainActivity : AppCompatActivity() {

   val binding : ActivityMainBinding by lazy {
       ActivityMainBinding.inflate(layoutInflater)
   }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)
//        supportActionBar?.apply {
//            title = getString(R.string.app_name)
////            subtitle = "Subtitle"
//            setDisplayHomeAsUpEnabled(true) // Show back button
////            setHomeAsUpIndicator(R.drawable.ic_launcher_foreground) // Set custom back button icon
//        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val manager = supportFragmentManager // We call fragment manager and take supportFragmentManager
        val tr = manager.beginTransaction() // Start Transaction
            tr.replace(R.id.frame, BlankFragment1()) // We change the frane from fragment

       tr.addToBackStack(null)
        tr.commit() //  This will reflect transition (Final Done )
        // manager and tr are variables

        binding.button2.setOnClickListener {
            var manager = supportFragmentManager
            val tr = manager.beginTransaction()
            tr.replace(R.id.frame,BlankFragment1())
            tr.addToBackStack(null)
            tr.commit()
        }
        binding.button3.setOnClickListener {
            var manager = supportFragmentManager
            val tr = manager.beginTransaction()
            tr.replace(R.id.frame,BlankFragment2())
            tr.addToBackStack(null)
            tr.commit()
        }


        }
    }
