package com.example.a18alertdialog

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Delete Files")
            dialog.setMessage("Do you want to delete this file ?")
            dialog.setIcon(R.drawable.delete)
            dialog.setPositiveButton("Yes"){dialoInterface,which->
    Toast.makeText(this,"Clicked Yes",Toast.LENGTH_SHORT).show()
            }
            dialog.setNegativeButton("No"){dialoInterface,which->
    Toast.makeText(this,"Clicked No",Toast.LENGTH_SHORT).show()
            }
            dialog.setNeutralButton("Cancel"){dialoInterface,which->
                Toast.makeText(this,"Clicked Cancel",Toast.LENGTH_SHORT).show()
            }
        val alertDialog:AlertDialog = dialog.create()
//            alertDialog.setCancelable(true) // screen par tap karne se  yeah  alert dialog box hat jayenga
            alertDialog.setCancelable(false) // screen par tap karne se  yeah  alert dialog box nahi hat jayenga


            alertDialog.show()

        }

    }
}