package com.example.mvvmandroomdatabase

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmandroomdatabase.databinding.RvItemBinding

class RvAdapter (val context: Context,val list: List<DataEntity>):RecyclerView.Adapter<RvAdapter.ViewHolder>(){
    class ViewHolder(val binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root){}

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = RvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
    }
}