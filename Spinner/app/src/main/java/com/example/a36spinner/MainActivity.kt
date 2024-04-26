package com.example.a36spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView.Adapter

class MainActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner // here we declare spinner variable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val spinner = findViewById<Spinner>(R.id.spinner) // here we find spinner by findviewbyid
        val spinnerlist = listOf("Apple 🍎","Banana 🍌","Grapes 🍇","Oranges 🍊","Pineapple 🍍") // List of spinner items
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,spinnerlist) // Here we create a array adapter
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val selectedItem = parent?.getItemAtPosition(position).toString()
                Toast.makeText(parent?.context, "$selectedItem", Toast.LENGTH_SHORT).show()


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }
}