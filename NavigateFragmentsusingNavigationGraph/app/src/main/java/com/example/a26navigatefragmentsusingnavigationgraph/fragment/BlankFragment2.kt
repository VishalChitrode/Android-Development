package com.example.a26navigatefragmentsusingnavigationgraph.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.a26navigatefragmentsusingnavigationgraph.R
import com.example.a26navigatefragmentsusingnavigationgraph.databinding.FragmentBlank2Binding

class BlankFragment2 : Fragment() {
    private lateinit var binding: FragmentBlank2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlank2Binding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            Toast.makeText(context, "This is Second Fragment", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    companion object {

    }
}