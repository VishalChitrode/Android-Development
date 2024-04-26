package com.example.a29sharebundle

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a29sharebundle.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private  val binding: ActivitySecondBinding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
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

        val intent = intent // //  Here we create a variable into this activity to get data from first activity
        val tvname = intent.getStringExtra("name") // getStringExtra("name"): This method is used to retrieve a string extra from the intent.
                                                        // It takes a key as a parameter and returns the string value associated with that key
        val tvemail = intent.getStringExtra("email")
        val tvrollnumber = intent.getStringExtra("rollnumber") // Here we create a var into second activity to store the data fetch from first activity
        val tvmobilenumber = intent.getStringExtra("mobilenumber")
        binding.tvname.text = "Name : $tvname"
        binding.tvEmail.text = "Email : $tvemail"
        binding.tvRollNumber.text = "Roll Number : $tvrollnumber"
        binding.tvMobileNumber.text = "Mobile Number : $tvmobilenumber"




    }
}