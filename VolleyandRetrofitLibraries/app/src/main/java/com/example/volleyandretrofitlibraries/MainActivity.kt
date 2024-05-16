package com.example.volleyandretrofitlibraries

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {


    // here we create variables
    var url = "https://api.github.com/users"
    private var userInfoItem = arrayListOf<userinfoItem>()
    var userinfo = userinfo()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView :RecyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        val adapter = Adapter(this,userinfo)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val stringRequest = StringRequest(
            url,
            { response ->
                // Parse the JSON array into a list of user objects
                val gson = GsonBuilder().create()
                val userList: Array<userinfoItem> = gson.fromJson(response, Array<userinfoItem>::class.java)

                // Add each user object to userInfoItem list
                userInfoItem.addAll(userList)

                // Display the user information
//                Toast.makeText(this, userInfoItem.toString(), Toast.LENGTH_SHORT).show()
            },
            { error ->
                // Handle error
                Toast.makeText(this, "Failed: $error", Toast.LENGTH_SHORT).show()
            }
        )

        val volleyQueue = Volley.newRequestQueue(this)
        volleyQueue.add(stringRequest)

    }


}