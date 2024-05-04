package com.example.firebaserealtimedatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtimedatabase.databinding.ActivityAllNotesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllNotesActivity : AppCompatActivity() {
    private val binding: ActivityAllNotesBinding by lazy {
        ActivityAllNotesBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    private lateinit var DbRef: DatabaseReference
    private lateinit var noterecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        noterecyclerView = binding.notesRecyclerview
        noterecyclerView.layoutManager = LinearLayoutManager(this)
        auth = FirebaseAuth.getInstance()
        DbRef = FirebaseDatabase.getInstance().reference

        val currentuser = auth.currentUser // here we declare current user and get current user

        currentuser?.let { task ->
    val notereference = DbRef.child("Users").child(task.uid).child("Notes")
        .addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val notelist : MutableList<NoteItem> = mutableListOf<NoteItem>()

                for (noteSnapshot: DataSnapshot in snapshot.children){
                    val note:NoteItem? = noteSnapshot.getValue(NoteItem::class.java)
                  note?.let {
                      notelist.add(it)
                  }
//                    notelist.reverse()

                }
                    val adapter = NoteAdapter()
                noterecyclerView.adapter  = adapter


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }
}}