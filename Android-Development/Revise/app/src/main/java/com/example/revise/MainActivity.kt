package com.example.revise

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.revise.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.signincreatehere.setOnClickListener{
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.signinbutton.setOnClickListener{
            val signinusername = binding.signineditTextTextEmailAddress.text.toString()
            val signinpassword = binding.siginpeditTextTextPassword.text.toString()

            if (signinusername.isEmpty() || signinpassword.isEmpty()){
                Toast.makeText(this,"Please filled the fields",Toast.LENGTH_SHORT).show()
            }else {
                auth.signInWithEmailAndPassword(signinusername,signinpassword)
                    .addOnCompleteListener { taskId->
                        if (taskId.isSuccessful){
                            Toast.makeText(this,"Login Successfull",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
                        }

                    }

            }
        }

        binding.signincreatehere.setOnClickListener{
            Toast.makeText(this,"Register yourself",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }}
