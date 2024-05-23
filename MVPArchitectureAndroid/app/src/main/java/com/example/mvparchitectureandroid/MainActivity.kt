package com.example.mvparchitectureandroid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mvparchitectureandroid.Presenter.MainActivityPresenter
import com.example.mvparchitectureandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var presenter: MainActivityPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(this)

    }
    fun initView() {
        binding.counterTextView.text = presenter?.getCounter()
        binding.clickButton.setOnClickListener { presenter?.incrementValue() }
    }

    fun updateViewData() {
        binding.counterTextView.text = presenter?.getCounter()
    }
}