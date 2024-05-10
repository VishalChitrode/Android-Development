package com.example.todolist

data class NoteItem(val title:String, val task : String , val noteId : String){
    constructor():this("","","")
}
