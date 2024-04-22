package com.example.revise

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
<<<<<<<< HEAD:Revise/app/src/main/java/com/example/revise/MainActivity.kt
========
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        },3000)
>>>>>>>> origin/master:Revise/app/src/main/java/com/example/revise/SplashActivity.kt
    }
}