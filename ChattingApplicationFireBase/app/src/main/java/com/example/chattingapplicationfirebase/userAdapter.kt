package com.example.chattingapplicationfirebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class userAdapter (val context: Context, val userList: ArrayList<User>):RecyclerView.Adapter<userAdapter.userViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userAdapter.userViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.user,parent,false)
        return userViewHolder(view)
    }

    override fun onBindViewHolder(holder: userAdapter.userViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.textName.text = currentUser.name
        holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatActivity::class.java)

            intent.putExtra("name",currentUser.name)
            intent.putExtra("Email",currentUser.email)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return  userList.size
    }
    class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textName = itemView.findViewById<TextView>(R.id.text_name)
    }


}