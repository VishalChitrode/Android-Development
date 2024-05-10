package com.example.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.todolist.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
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
        auth = FirebaseAuth.getInstance()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("194592876563-h6b225t9c71qe8qf36fjbftbo2o1bnue.apps.googleusercontent.com")
            .requestEmail().build()

        binding.loginSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }
        binding.loginLoginbutton.setOnClickListener {
            val signinemail = binding.loginEmail.text.toString()
            val signinpassword = binding.loginPassword.text.toString()
        if (signinemail.isEmpty() || signinpassword.isEmpty())
        {
            Toast.makeText(this,"Please filled the fields",Toast.LENGTH_SHORT).show()
        }else{
            auth.signInWithEmailAndPassword(signinemail,signinpassword)
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
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
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    binding.loginGoogle.setOnClickListener {
        val signInClient = googleSignInClient.signInIntent // here we sign in google account
        launcher.launch(signInClient)
        startActivity(signInClient)
    }}
        private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
            if (result.resultCode == Activity.RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data) // here we get all the data whatever we fetch from when we signin from google account
// here we use signinIntent retrieves the GoogleSignInAccount object from the data returned by the activity result. This object contains information about the signed-in user,
                // such as their display name, email address, and profile picture.

                if (task.isSuccessful){
                    val account : GoogleSignInAccount? = task.result // here we get all the detail of the use in account variable
                    val credential = GoogleAuthProvider.getCredential(account?.idToken,null)

                    // here we get all user credentials
                    auth.signInWithCredential(credential).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this,"SignIn Successfully",Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,"SignIn Failed",Toast.LENGTH_SHORT).show()
                        }

                    }
                }

            }else{
                Toast.makeText(this,"Failed 👎",Toast.LENGTH_SHORT).show()
            }
    }
}