package com.example.chattingapplicationfirebase


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chattingapplicationfirebase.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUpActivity : AppCompatActivity() {
    private  val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var DbRef : DatabaseReference

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
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.signupRegistered.setOnClickListener{
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
                            //
                            addUserToDatabase(signupusername,auth.currentUser?.email,signuppassword)

                            val intent = Intent(this,SignInActivity::class.java)

                            startActivity(intent)
                            finish()

                        }
                        else{
                            Toast.makeText(this,"Registration Failed ${taskId.exception?.message}",Toast.LENGTH_SHORT).show()
                        }

                        auth = FirebaseAuth.getInstance()
                        auth.signOut() // place here to direct go on login activity
                    }
            }
        }


    }
    private fun addUserToDatabase(name: String, email: String?, password: String) {
        email?.let {
            DbRef = FirebaseDatabase.getInstance().getReference("user")
            DbRef.child(it.replace(".", ",")).setValue(User(name, it, password))
        }
    }
}