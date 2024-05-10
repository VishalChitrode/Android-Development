package com.example.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.NotesItemBinding

class NoteAdapter(
    private var notes: List<NoteItem>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    interface OnItemClickListener {
        fun ondeleteclick(noteId: String)
        fun onupdateclick(noteId: String,title:String,task:String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note: NoteItem = notes[position]
        holder.bind(note)
        holder.binding.delete.setOnClickListener {
            itemClickListener.ondeleteclick(note.noteId)
        }
        holder.binding.update.setOnClickListener {
            itemClickListener.onupdateclick(note.noteId,note.task,note.title)
        }
    }

    inner class NoteViewHolder(val binding: NotesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteItem) {
            binding.noteNoteItem.text = note.task
            binding.titleNotesItem.text = note.title
        }
    }
}
