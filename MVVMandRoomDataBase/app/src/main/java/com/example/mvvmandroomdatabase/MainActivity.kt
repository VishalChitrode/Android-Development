package com.example.mvvmandroomdatabase

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmandroomdatabase.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<DataEntity>()
    private val rvAdapter by lazy {
        RvAdapter(this, list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.addAll(SampleData.data)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = rvAdapter





        binding.floatingActionButton.setOnClickListener { view ->
            startActivity(Intent(this, ScrollingActivity::class.java))
        }
    }
}

