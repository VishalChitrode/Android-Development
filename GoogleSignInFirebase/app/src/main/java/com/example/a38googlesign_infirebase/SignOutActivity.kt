package com.example.a38googlesign_infirebase

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a38googlesign_infirebase.databinding.ActivitySignOutBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class SignOutActivity : AppCompatActivity() {
    private val binding: ActivitySignOutBinding by lazy {
        ActivitySignOutBinding.inflate(layoutInflater)
    }
    private lateinit var auth : FirebaseAuth
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
        binding.signoutButton.setOnClickListener{
            auth = FirebaseAuth.getInstance()
            auth.signOut() // place here to direct go on login activity

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("11246471149-t2pvr1sb3tnip42qpc6ggdp236mnhdj6.apps.googleusercontent.com")
                .requestEmail().build()
GoogleSignIn.getClient(this,gso).signOut()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}