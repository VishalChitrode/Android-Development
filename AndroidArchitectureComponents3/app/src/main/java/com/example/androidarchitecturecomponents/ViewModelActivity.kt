package com.example.androidarchitecturecomponents

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.androidarchitecturecomponents.databinding.ActivityViewModelBinding

class ViewModelActivity : AppCompatActivity() {
    private val binding : ActivityViewModelBinding by lazy {
        ActivityViewModelBinding.inflate(layoutInflater)
    }
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_model)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setTexts()
    }
    fun setTexts(){
        binding.textView.text = mainViewModel.count.toString()
    }
    fun increment(view: View){
        mainViewModel.increment()
        setTexts()
    }

}