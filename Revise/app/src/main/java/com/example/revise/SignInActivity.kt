package com.example.revise

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.revise.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private val binding: ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }

    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }



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
        binding.sigininCreatenewbutton.setOnClickListener{
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.signinButton.setOnClickListener{
            val signinemailaddress = binding.signinEnteremailaddress.text.toString()
            val singinpassword = binding.signinEnterpassword.text.toString()

            if (signinemailaddress.isEmpty() || singinpassword.isEmpty()){
                Toast.makeText(this,"Please filled all the fields",Toast.LENGTH_SHORT).show()

            }else{
                auth.signInWithEmailAndPassword(signinemailaddress,singinpassword)
                    .addOnCompleteListener { taskId ->
                        if (taskId.isSuccessful){
                            Toast.makeText(this,"Login Successfull",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
                        }

                    }
            }


        }
    }
}