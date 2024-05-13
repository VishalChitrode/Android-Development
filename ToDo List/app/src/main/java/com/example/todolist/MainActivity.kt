package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.UpdateBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity(), NoteAdapter.OnItemClickListener {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
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

        binding.mainSignout.setOnClickListener {
            auth = FirebaseAuth.getInstance()
            auth.signOut() // place here to direct go on login activity

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("194592876563-em7co61p9dta5s4v3em48247kupb15pd.apps.googleusercontent.com")
                .requestEmail().build()
            GoogleSignIn.getClient(this,gso).signOut()
            startActivity(Intent(this,LoginActivity::class.java))

        }
        binding.createButton.setOnClickListener {
            startActivity(Intent(this,CreateActivity::class.java))
        }


        noterecyclerView = binding.noterecyclerView
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
                        val adapter = NoteAdapter(notelist,this@MainActivity)
                        noterecyclerView.adapter  = adapter


                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })


        }
    }

    override fun ondeleteclick(noteId: String) {
        val currentuser = auth.currentUser
        currentuser?.let { user->
            val noteReference : DatabaseReference = DbRef.child("Users").child(user.uid)
                .child("Notes").child(noteId)
            noteReference.removeValue()

        }
    }

    override fun onupdateclick(noteId: String,newtitle :String,newtask:String) {
        val dialogBinding : UpdateBinding = UpdateBinding.inflate(LayoutInflater.from(this))
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setTitle("Update Note")
            .setPositiveButton("Update") { dialog, which ->
                val newtitle = dialogBinding.updateTitle.text.toString()
                val newtask = dialogBinding.updateNote.text.toString()
                onupdateNote(noteId, newtitle, newtask)
                dialog.dismiss()

            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .create()
        dialogBinding.updateTitle.setText(newtitle)
        dialogBinding.updateNote.setText(newtask)
        dialog.show()
        }
    private fun onupdateNote(noteId: String, newtitle: String, newtask: String) {
        val currentuser = auth.currentUser
        val updatetask = NoteItem(noteId,newtitle,newtask)
        currentuser?.let { user ->
            val noteReference: DatabaseReference = DbRef.child("Users").child(user.uid)
                .child("Notes").child(noteId)
            noteReference.child(noteId).setValue(updatetask)
                .addOnCompleteListener { taskId ->
                    if (taskId.isSuccessful) {
                        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()


                    }


}
                }
        }}