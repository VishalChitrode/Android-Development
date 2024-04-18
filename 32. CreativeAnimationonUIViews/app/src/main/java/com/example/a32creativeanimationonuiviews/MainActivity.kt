package com.example.a32creativeanimationonuiviews

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a32creativeanimationonuiviews.databinding.ActivityMainBinding

import render.animations.Bounce
import render.animations.Render


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
        val render = Render(this)

// Set Animation
        render.setAnimation(Bounce().InDown(binding.spinkit))
        render.setDuration(3000)
        render.start()


        binding.spinkit.setOnClickListener{
            val render = Render(this)

// Set Animation
            render.setAnimation(Bounce().InDown(binding.spinkit))

            render.start()
        }
    }

}