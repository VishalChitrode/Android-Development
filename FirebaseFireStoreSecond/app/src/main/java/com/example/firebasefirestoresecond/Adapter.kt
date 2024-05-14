package com.example.firebasefirestoresecond

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasefirestoresecond.databinding.ItemBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class Adapter(var context: Context, private var list: ArrayList<User>): RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(val binding: ItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.name.text = list.get(position).name
        holder.binding.password.text = list.get(position).password
        holder.binding.delete.setOnClickListener {
            val db = Firebase.firestore
            db.collection("users").document(list.get(position).id!!).delete()
                .addOnCompleteListener {
                    Toast.makeText(context,"Deleted",Toast.LENGTH_SHORT).show()
                    list.removeAt(position)
                    notifyDataSetChanged()
                }.addOnFailureListener {
                    Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
                }
        }
        holder.binding.update.setOnClickListener {
            val intent = Intent(context,MainActivity2::class.java)
            intent.putExtra("id",list.get(position).id)
            intent.putExtra("name",list.get(position).name)
            intent.putExtra("password",list.get(position).password)
            context.startActivity(intent)
        }
    }
}