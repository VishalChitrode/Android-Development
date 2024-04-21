package com.example.a37loginandsignupusingfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a37loginandsignupusingfirebase.databinding.ActivitySignUpBinding
import com.example.a37loginandsignupusingfirebase.databinding.ActivityWelcomeBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private  val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.signupLogin.setOnClickListener{
            Toast.makeText(this,"Sign up Successfully",Toast.LENGTH_SHORT).show()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.signupRegistered.setOnClickListener {
            val signupemail = binding.signupEmailaddress.text.toString() // here we get the data from signup activity fileds
            val signupusername = binding.signupUsername.text.toString()
            val signuppassword = binding.signupPassword.text.toString()
            val signupreenteredpassword = binding.signupReenterpassword.text.toString()

            if (signupemail.isEmpty() || signupusername.isEmpty() || signuppassword.isEmpty() || signupreenteredpassword.isEmpty()){

                Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
            }else if(signuppassword!=signupreenteredpassword){
                    Toast.makeText(this,"Password doesn't match",Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(signupemail,signuppassword)
                    .addOnCompleteListener { taskId ->
                        if (taskId.isSuccessful){
                            Toast.makeText(this,"Registration Successfully",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this,"Registration Failed ${taskId.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }


    }
}