package com.example.a29sharebundle

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a29sharebundle.databinding.ActivityMainBinding
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // here we use binding to find view id's
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.button.setOnClickListener {
           val name =  binding.Name.text.toString() // we use name variable to store whatever we store in "Name" variable
           val email =  binding.email.text.toString() // we use name variable to store whatever we store in "email" variable
           val rollnumber =  binding.rollnumber.text.toString() // we use name variable to store whatever we store in "rollnumber" variable
           val mobilenumber =  binding.mobilenumber.text.toString() // we use name variable to store whatever we store in "mobilenumber" variable


            // here we declare a intent variable
            val intent = Intent(this,SecondActivity::class.java) // here we use this intent method to send data from one screen to second activity
            intent.putExtra("name",name) // by using this we carry this data too into name var. of this putExtra method
            intent.putExtra("email",email) // by using this we carry this data too into email var. of this putExtra method
            intent.putExtra("rollnumber",rollnumber) // by using this we carry this data too into rollnumber var. of this putExtra method
            intent.putExtra("mobilenumber",mobilenumber) // by using this we carry this data too into mobilenumber var of this putExtra method
            startActivity(intent)

        }

    }
}