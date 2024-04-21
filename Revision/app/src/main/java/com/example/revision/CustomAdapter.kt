package com.example.revision

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.revision.databinding.ActivityMainBinding
import com.example.revision.databinding.ChatcardviewBinding


class CustomAdapter(var datalist : ArrayList<ItemsViewModel>,var context: Context): RecyclerView.Adapter<CustomAdapter.MyViewHolder>(){ // RecyclerView.Adapter is a class provided by the Android framework for adapting data to RecyclerViews.
   //<MyViewHolder> specifies the type of ViewHolder that this adapter will use.


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder { // jab view create honga tab kya karna hain
        //onCreateViewHolder: This method is responsible for creating ViewHolder objects. It is called when RecyclerView needs a new ViewHolder to represent an item.
//        var view = LayoutInflater.from(context).inflate(R.layout.chatcardview,parent,false)
//        return MyViewHolder(view) //here we return ViewHolder

        var binding = ChatcardviewBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int { // kitne count tak element show honga
        //getItemCount: This method returns the total number of items in the data set.
       return datalist.size //jitni size hongi data list ki wo return kardengs
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) { // click karne par kya honga
        //onBindViewHolder: This method is responsible for binding data to ViewHolder objects. It is called by RecyclerView to display the data at the specified position.

        holder.binding.profileImage.setImageResource(datalist.get(position).profilephoto)
        holder.binding.message.text = datalist.get(position).message
        holder.binding.contact.text = datalist.get(position).contactname
        holder.binding.time.text = datalist.get(position).time
        holder.binding.pin.setImageResource(datalist.get(position).pin)
    }
    inner class MyViewHolder(var binding: ChatcardviewBinding) : RecyclerView.ViewHolder(binding.root)

}