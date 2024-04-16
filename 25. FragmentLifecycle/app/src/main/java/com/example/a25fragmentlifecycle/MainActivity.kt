package com.example.a25fragmentlifecycle

import android.os.Bundle
import android.view.Choreographer.FrameData
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a25fragmentlifecycle.fragment.BlankFragment1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var manager = supportFragmentManager
        var tr = manager.beginTransaction()
        tr.replace(R.id.main,BlankFragment1())
        tr.addToBackStack(null)
        tr.commit()
    }
}