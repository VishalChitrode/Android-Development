package com.example.volleyandretrofitlibraries

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(val context: Context, val userInfoList: List<userinfoItem>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.singleraw, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userInfoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userInfoItem = userInfoList[position]
        Glide.with(context).load(userInfoItem.avatar_url).into(holder.image)
        holder.name.text = userInfoItem.login
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.UserImage)
        val name: TextView = itemView.findViewById(R.id.textView)
    }
}
