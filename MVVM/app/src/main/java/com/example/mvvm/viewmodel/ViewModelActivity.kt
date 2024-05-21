package com.example.mvvm.viewmodel

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.R
import com.example.mvvm.viewmodelfactory.MainViewModelFactory

class ViewModelActivity : AppCompatActivity() {
    lateinit var txtcounter : TextView
    lateinit var viewmodel :  MainViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_model)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewmodel = ViewModelProvider(this,MainViewModelFactory(10)).get(MainViewModel::class.java)
        txtcounter = findViewById(R.id.textView)
        setTexts()

    }

    fun setTexts(){
        txtcounter.text = viewmodel.count.toString()
    }
    fun increment(v: View){
        viewmodel.increment()
        setTexts()
    }
}