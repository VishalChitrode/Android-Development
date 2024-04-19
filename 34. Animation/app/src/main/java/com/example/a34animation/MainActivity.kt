package com.example.a34animation

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a34animation.databinding.ActivityMainBinding

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

        binding.fadein.setOnClickListener{
            val animationFadein = AnimationUtils.loadAnimation(this,R.anim.fade_in)
            binding.textView.startAnimation(animationFadein)
        }
        binding.fadeout.setOnClickListener{
            val animationFadeout = AnimationUtils.loadAnimation(this,R.anim.fade_out)
            binding.textView.startAnimation(animationFadeout)
        }
        binding.zoomin.setOnClickListener{
            val animationzoomin = AnimationUtils.loadAnimation(this,R.anim.zoom_in)
            binding.textView.startAnimation(animationzoomin)
        }
        binding.zoomout.setOnClickListener{
            val animationzoomout = AnimationUtils.loadAnimation(this,R.anim.zoom_out)
            binding.textView.startAnimation(animationzoomout)
        }
        binding.slidedown.setOnClickListener{
            val animationslidedown = AnimationUtils.loadAnimation(this,R.anim.slide_down)
            binding.textView.startAnimation(animationslidedown)
        }
        binding.slideup.setOnClickListener{
            val animationslideup = AnimationUtils.loadAnimation(this,R.anim.slide_up)
            binding.textView.startAnimation(animationslideup)
        }
        binding.rotate.setOnClickListener{
            val animationrotate = AnimationUtils.loadAnimation(this,R.anim.rotate)
            binding.textView.startAnimation(animationrotate)
        }
        binding.bounce.setOnClickListener{
            val animationbounce = AnimationUtils.loadAnimation(this,R.anim.bounce)
            binding.textView.startAnimation(animationbounce)
        }
    }
}