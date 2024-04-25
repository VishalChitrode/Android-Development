package com.example.chattingapplicationfirebase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ChatActivity : AppCompatActivity() {
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var MessageBox : EditText
    private lateinit var sendbutton : ImageView
    private lateinit var messageList : ArrayList<Message>
    private lateinit var messageAdapter  : MessageAdapter
    private lateinit var DbRef : DatabaseReference

    var receiverRoom:String? = null
    var senderRoom:String? = null
    var senderemail: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = Intent()
        val name = intent.getStringExtra("name")
        senderemail = intent.getStringExtra("email")
        val receiveremail = intent.getStringExtra("email")

        val senderUid = FirebaseAuth.getInstance().currentUser?.email
        DbRef = FirebaseDatabase.getInstance().getReference()
        senderRoom = receiveremail + senderUid
        receiverRoom = senderUid + receiveremail

        supportActionBar?.title = name

        chatRecyclerView = findViewById(R.id.chatrecyclerView)
        MessageBox = findViewById(R.id.messageBox)
        sendbutton = findViewById(R.id.sendbutton)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this,messageList)

        sendbutton.setOnClickListener {
            val message = MessageBox.text.toString()
            val messageObject = senderemail?.let { it1 -> Message(message, it1) }

            DbRef.child("chats").child(senderRoom!!).child("message").push()
                .setValue(messageObject).addOnCompleteListener{
                    DbRef.child("chats").child(receiverRoom!!).child("message").push()
                        .setValue(messageObject)
                }
        }
    }
}