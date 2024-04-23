package com.example.revise

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.revise.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

   private  val binding :ActivitySignupBinding by lazy {
       ActivitySignupBinding.inflate(layoutInflater)
   }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.siginupLoginhere.setOnClickListener {
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.signupButton.setOnClickListener{
            val signupusername = binding.signupEnterusername.text.toString()
            val signupemailaddress = binding.signupEnteremailaddress.text.toString()
            val signuppassword = binding.signupEnterpassword.text.toString()
            val signupreenterpassword = binding.signupReenterpassword.text.toString()

            if (signupusername.isEmpty() || signupemailaddress.isEmpty() || signuppassword.isEmpty() || signupreenterpassword.isEmpty()){
                Toast.makeText(this,"Please  all the fields",Toast.LENGTH_SHORT).show()
            }else if (signuppassword != signupreenterpassword){
                Toast.makeText(this,"Password doesn't matched",Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(signupemailaddress,signuppassword)
                    .addOnCompleteListener { taskId ->
                        if (taskId.isSuccessful){
                            Toast.makeText(this,"Registration SuccessFull",Toast.LENGTH_SHORT).show()
                            Log.d(TAG,"Here registration successfully complete and goto login page")
                            val intent = Intent(this,SignInActivity::class.java)
                            startActivity(intent)
                            finish()

                        }
                        else
                        {
                            Toast.makeText(this,"Registration Failed ${taskId.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                        auth = FirebaseAuth.getInstance()
                        auth.signOut() // place here to direct go on login activity
                    }
            }
        }

    }
}