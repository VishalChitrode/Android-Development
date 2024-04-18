package com.example.a27implicitintent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a27implicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  val binding: ActivityMainBinding by lazy { // here we enable view binding
        ActivityMainBinding.inflate(layoutInflater) //
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root) // with the help of this we can access all views without find by id's
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.webpage.setOnClickListener {
            val intent = Intent( // here we make intent variables and we call intents method
                Intent.ACTION_VIEW,
                Uri.parse("https://www.geeksforgeeks.org/implicit-and-explicit-intents-in-android-with-examples/")
            ) // for URL we use Uri parse method
            startActivity(intent) // Here we start intent
        }
        binding.opencamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // here we create a intent variable and use Mediastore with Action Image Capture
            startActivity(intent)
        }
        binding.makecall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL) // Action Dial here
            intent.data = Uri.parse("tel:+917987265650") // here we insert data for calling
            startActivity(intent) // here we start intent
        }
        binding.button.setOnClickListener {
            val text = binding.editTextText.text.toString() // here we create variable and get text with help of toString()
            val intent = Intent(Intent.ACTION_SEND) // here we create intent var and we use to share the above text
            intent.type = "text/plain" // here we define intent type
            intent.putExtra(Intent.EXTRA_TEXT, text) // here we
            startActivity(Intent.createChooser(intent, "Share Via")) // here we use creatorchooser
        }
    }
}