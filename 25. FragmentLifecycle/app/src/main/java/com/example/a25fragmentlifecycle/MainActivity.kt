package com.example.a25fragmentlifecycle

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.a25fragmentlifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button = findViewById<Button>(R.id.button)
button.setOnClickListener {
    val Fragment = BlankFragment()
    var manager = supportFragmentManager.beginTransaction()
    manager.replace(R.id.frameLayout,Fragment)
    manager.addToBackStack(null)
    manager.commit()


}

    }
}