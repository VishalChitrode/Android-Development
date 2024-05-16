package com.example.volley

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.easylearn.User
import com.example.easylearn.UserAdapter

class MainActivity : AppCompatActivity() {
    var userList = arrayListOf<User>()
    val apiSample = "https://reqres.in/api/users"
    var recyclerView: RecyclerView? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView =  findViewById(R.id.recyclerView)

        val reqQueue: RequestQueue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(Request.Method.GET,apiSample, null, { res ->
            Log.d("Volley Sample", res.getString("page"))

            val jsonArray = res.getJSONArray("data")
            for (i in 0 until jsonArray.length()){
                val jsonObj = jsonArray.getJSONObject(i)

                val user = User(
                    jsonObj.getInt("id"),
                    jsonObj.getString("email"),
                    jsonObj.getString("first_name"),
                    jsonObj.getString("last_name"),
                    jsonObj.getString("avatar")
                )

                userList.add(user)
            }

            recyclerView?.layoutManager = LinearLayoutManager(this)
            recyclerView?.adapter = UserAdapter(userList)



        }, {err ->
            Log.d("Volley Sample Fail", err.message.toString())
        })

        reqQueue.add(request)

    }
}