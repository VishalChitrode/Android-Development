package com.example.a30animatedbottomnavigationbar


import android.os.Bundle
import android.util.Log

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a30animatedbottomnavigationbar.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {
    private  val binding: ActivityMainBinding by lazy {
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
//        binding.bottomBar.onTabSelected = {
//            Log.d("bottom_bar", "Selected tab: " + it.title)
//        }
//        binding.bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
//            override fun onTabSelected(
//                lastIndex: Int,
//                lastTab: AnimatedBottomBar.Tab?,
//                newIndex: Int,
//                newTab: AnimatedBottomBar.Tab
//            ) {
//                Log.d("bottom_bar", "Selected index: $newIndex, title: ${newTab.title}")
//            }
//
//            // An optional method that will be fired whenever an already selected tab has been selected again.
//            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
//                Log.d("bottom_bar", "Reselected index: $index, title: ${tab.title}")
//            }
//        })
        binding.bottombar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                Log.d("bottom_bar", "Selected index: $newIndex, title: ${newTab.title}")
            }

            // An optional method that will be fired whenever an already selected tab has been selected again.
            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                Log.d("bottom_bar", "Reselected index: $index, title: ${tab.title}")
            }


        })
    }}



