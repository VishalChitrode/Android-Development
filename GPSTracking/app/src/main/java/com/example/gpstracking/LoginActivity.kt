package com.example.gpstracking

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gpstracking.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private  val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
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
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        binding.signinLogin.setOnClickListener {
            val signinusername = binding.loginEmailaddress.text.toString()
            val signinpassword = binding.loginPassword.text.toString()

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

        binding.loginSignup.setOnClickListener{
            Toast.makeText(this,"Register your self",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }}
