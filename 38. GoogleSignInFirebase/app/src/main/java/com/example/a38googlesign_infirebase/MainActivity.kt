package com.example.a38googlesign_infirebase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a38googlesign_infirebase.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient // google signin client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.show()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("11246471149-t2pvr1sb3tnip42qpc6ggdp236mnhdj6.apps.googleusercontent.com")
            .requestEmail().build()

        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignIn.getClient(this, gso) //GoogleSignInClient object with the provided configuration options (gso) and the current context (this). This GoogleSignInClient object can then be used to initiate the Google Sign-In flow in your app

        binding.signinFacebooklogin.setOnClickListener {
            Toast.makeText(this, "Tap on FaceBook", Toast.LENGTH_SHORT).show()
        }
        binding.signinInstagramlogin.setOnClickListener {
            Toast.makeText(this, "Tap on Instagram", Toast.LENGTH_SHORT).show()
        }
        binding.signinGooglelogin.setOnClickListener {
            Toast.makeText(this, "Tap on Google", Toast.LENGTH_SHORT).show()
            val signInClient = googleSignInClient.signInIntent // here we sign in google account
           launcher.launch(signInClient)
            startActivity(signInClient) // and launch gooogle signin activity
        }


    }

    //This is used to sets up an Activity Result Launcher named launcher using the registerForActivityResult method, which will handle the result of starting an activity for result. When the activity result is returned,
    // the lambda expression specified in the second parameter will be invoked to handle the result.
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
                    Toast.makeText(this,"Done 👍",Toast.LENGTH_SHORT).show()
                   val intent = Intent(this,SignOutActivity::class.java)
                   startActivity(intent)
               }else{
                   Toast.makeText(this,"Failed 👎",Toast.LENGTH_SHORT).show()
               }

           }
       }

        }else{
            Toast.makeText(this,"Failed 👎",Toast.LENGTH_SHORT).show()
        }

    }

    }
