package com.example.firebaserealtimedatabase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebaserealtimedatabase.databinding.ActivityCreateBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateActivity : AppCompatActivity() {
    private val binding: ActivityCreateBinding by lazy {
        ActivityCreateBinding.inflate(layoutInflater)
    }
    private lateinit var auth : FirebaseAuth
    private lateinit var DbRef : DatabaseReference // here we declare DB ref variable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        auth = FirebaseAuth.getInstance()
        DbRef = FirebaseDatabase.getInstance().reference // here we initialize the DB ref
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.createButton.setOnClickListener {
            // here we get the text and store in title and note variable
            val title = binding.createTitle.text.toString()
            val note = binding.createNote.text.toString()
           if(title.isEmpty() && note.isEmpty()){
                Toast.makeText(this,"Add Title & Note",Toast.LENGTH_SHORT).show()
            }else {
                val currentuser = auth.currentUser // here we get the value of current user
               currentuser?. let { user->
                   //Generate unique key for every notes
                   val noteKey = DbRef.child("Users").child("Uid").child("Notes").push().key
                   val noteitem = NoteItem(title,note)
                   if (noteKey != null){
                       DbRef.child("Users").child(user.uid).child("Notes").child(noteKey).setValue(noteitem)
                           .addOnCompleteListener { task ->
                               if (task.isSuccessful){
                                   Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show()
                               }else
                               {
                                   Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                               }
                           }
                   }
               }
            }

        }
    }
}