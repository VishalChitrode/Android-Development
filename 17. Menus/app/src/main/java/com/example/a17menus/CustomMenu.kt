package com.example.a17menus

import android.content.Context
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import java.lang.Exception

class CustomMenu {
    fun showMenu(context:Context, view: View){


        val pop = PopupMenu(context,view)
        pop.inflate(R.menu.menu)
        pop.setOnMenuItemClickListener {
            when(it!!.itemId){
                R.id.action_edit -> {
                    Toast.makeText(context,"Clicked on edit",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_delete -> {
                    Toast.makeText(context,"Clicked on delete",Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_shared -> {
                    Toast.makeText(context,"Clicked on share",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        try {
            val fieldMpopuup = PopupMenu::class.java.getDeclaredField("mPopup") //Here we intilaized PopupMenu
            fieldMpopuup.isAccessible = true // property ko true kar rahe hain
            val mPopup = fieldMpopuup.get(pop)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(mPopup,true)




        }catch (e: Exception){

        }finally {
            pop.show()
        }

    }
}