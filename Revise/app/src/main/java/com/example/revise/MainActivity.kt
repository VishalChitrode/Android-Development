package com.example.revise

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.revise.fragment.CallsFragment
import com.example.revise.fragment.ChatsFragment
import com.example.revise.fragment.CommunitiesFragment
import com.example.revise.fragment.StatusFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        loadFragment(ChatsFragment())
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.selectedItemId = R.id.chat
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.chat -> {
                    loadFragment(ChatsFragment())
                    true
                }
                R.id.status -> {
                    loadFragment(StatusFragment())
                    true
                }
                R.id.communities -> {
                    loadFragment(CommunitiesFragment())
                    true
                }
                R.id.calls -> {
                    loadFragment(CallsFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment)
        transaction.commit()
    }
    }
