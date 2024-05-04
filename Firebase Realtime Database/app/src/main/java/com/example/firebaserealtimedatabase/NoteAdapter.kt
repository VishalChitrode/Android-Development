package com.example.firebaserealtimedatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtimedatabase.databinding.NotesItemBinding

class NoteAdapter(private var context: List<NoteItem>, private var notes: List<NoteItem>, private val onItemClickListener: (NoteItem) -> Unit):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note : NoteItem = notes[position]
        holder.bin(note)

    }


    class NoteViewHolder(private val binding:NotesItemBinding) :RecyclerView.ViewHolder(binding.root){
fun bin(note : NoteItem){
    binding.noteNoteItem.text = note.note
    binding.titleNotesItem.text = note.title
}
    }
}