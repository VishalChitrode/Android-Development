package com.example.a24fragments.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a24fragments.R
import com.example.a24fragments.databinding.ActivityMainBinding.inflate
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
       binding = FragmentBlank1Binding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            Toast.makeText(context,"This is First Fragment",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    companion object {

                }
            }
