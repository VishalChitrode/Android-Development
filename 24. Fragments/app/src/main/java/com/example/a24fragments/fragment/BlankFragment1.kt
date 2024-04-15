package com.example.a24fragments.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a24fragments.R
import com.example.a24fragments.databinding.FragmentBlank1Binding

class BlankFragment1 : Fragment() {
   private lateinit var binding: FragmentBlank1Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentBlank1Binding.inflate(inflater, container, false) // simple convert it into view

       binding.button.setOnClickListener {
           Toast.makeText(context,"This is Toast",Toast.LENGTH_SHORT).show()
//here we use binding to call first fragment button and set setonclicklistner
       }

        return binding.root // Here we return view
    }

    companion object {


            }
    }
