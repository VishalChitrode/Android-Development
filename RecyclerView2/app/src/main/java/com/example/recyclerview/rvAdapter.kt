package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.CardviewBinding

class rvAdapter(val list:ArrayList<rvModel>,var context:Context):RecyclerView.Adapter<rvAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        var view = LayoutInflater.from(context).inflate(R.layout.cardview,parent,false) // yaha par hum file pass kar rahe hain
//        return MyViewHolder(view)
        var binding = CardviewBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // yaha par kis par click karne par kya honga wo add karenge
anim(holder.itemView)
        holder.binding.image.setImageResource(list.get(position).image)
        holder.binding.address.text = list.get(position).address
        holder.binding.name.text = list.get(position).name
        holder.itemView.setOnClickListener{
            Toast.makeText(context,list.get(position).name,Toast.LENGTH_SHORT).show()
        }
    }
    inner class MyViewHolder(var binding: CardviewBinding) : RecyclerView.ViewHolder(binding.root){

    }
    fun anim(view: View){
        var animation = AlphaAnimation(0.0f,1.0f)
        animation.duration=500
        view.startAnimation(animation)

    }


}