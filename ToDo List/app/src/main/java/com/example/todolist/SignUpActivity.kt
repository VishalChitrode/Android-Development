package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolist.databinding.ActivitySignUpBinding
import com.example.todolist.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding : ActivitySignUpBinding by  lazy {
    ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
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
        binding.signupSignin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        binding.registerButton.setOnClickListener {
            val signupemail = binding.signupEmail.text.toString() // here we get the data from signup activity fileds
            val signupusername = binding.signupUsername.text.toString()
            val signuppassword = binding.signupPassword.text.toString()
            val signupreenteredpassword = binding.signupReenteredpassword.text.toString()
            if (signupemail.isEmpty() || signupusername.isEmpty() || signuppassword.isEmpty() || signupreenteredpassword.isEmpty()){
                Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show()
        }else if (signuppassword != signupreenteredpassword){
                Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show()
            }
            auth.createUserWithEmailAndPassword(signupemail,signuppassword)
                .addOnCompleteListener(){ task ->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Registration Successfully",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,LoginActivity::class.java)
                        startActivity(intent)
                    } else{
                        Toast.makeText(this,"Registration Failed ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                    }

                }
auth.signOut()
        }
    }
}