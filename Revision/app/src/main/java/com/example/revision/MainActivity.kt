package com.example.revision

import android.content.Intent
import android.os.Bundle
import android.view.Display.Mode
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revision.databinding.ActivityMainBinding
import com.example.revision.databinding.ChatcardviewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: CustomAdapter
    private lateinit var datalist : ArrayList<ItemsViewModel>

    private lateinit var recyclerview :RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val bottomBar = findViewById<AnimatedBottomBar>(R.id.bottom_bar)

        bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(lastIndex: Int, lastTab: AnimatedBottomBar.Tab?, newIndex: Int, newTab: AnimatedBottomBar.Tab) {
                when (newTab.id) {
                    R.id.updates -> {
                        // Handle updates tab click
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main, UpdatesFragment())
                            .commit()
                        true
                    }
                    R.id.communities -> {
                        // Handle communities tab click
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main, CommunitiesFragment())
                            .commit()
                        true
                    }
                    R.id.calls -> {
                        // Handle calls tab click
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main, CallsFragment())
                            .commit()
                        true
                    }
                }
            }

            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                // Handle tab reselection
            }
        })

        datalist = ArrayList<ItemsViewModel>()
        datalist.add(ItemsViewModel(R.drawable.krishan,"Vishal chitrode","Hello","12:00 pm",R.drawable.pin))
        datalist.add(ItemsViewModel(R.drawable.krishan,"Pritesh chitrode","Hey","11:00 pm",R.drawable.pin))
        datalist.add(ItemsViewModel(R.drawable.krishan,"Pooja chitrode","Hii","10:00 pm",R.drawable.pin))
        datalist.add(ItemsViewModel(R.drawable.krishan,"Deepak chitrode","Busy","9:00 pm",R.drawable.pin))
        datalist.add(ItemsViewModel(R.drawable.krishan,"Pushpa chitrode","WhatsApp Clone","8:00 pm",R.drawable.pin))

        adapter = CustomAdapter(datalist,this)
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = adapter
    }
}