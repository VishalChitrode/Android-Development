package com.example.a26navigatefragmentsusingnavigationgraph

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.a26navigatefragmentsusingnavigationgraph.databinding.FragmentBlank1Binding

class BlankFragment1 : Fragment() {
    private lateinit var binding: FragmentBlank1Binding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentBlank1Binding.inflate(inflater, container, false) // simple convert it into view

        binding.button.setOnClickListener {
//            Toast.makeText(context,"This is Toast",Toast.LENGTH_SHORT).show()


        navController.navigate(R.id.action_blankFragment1_to_blankFragment2)

//here we use binding to call first fragment button and set setonclicklistner
        }

        return binding.root // Here we return view
    }

    companion object {


    }
}