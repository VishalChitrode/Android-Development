package com.example.a25fragmentlifecycle

import android.content.ContentValues.TAG
import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


class BlankFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TAG","OnAttach Called: View Created")
        Toast.makeText(requireContext(),"Onattach Called",Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG","OnCreate Called: View Created")
        Toast.makeText(requireContext(),"OnCreate Called",Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
        Log.d("TAG","OnCreatedView Called: View Created")
        Toast.makeText(requireContext(),"OnCreateView Called",Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG","OnViewCreated Called: View Created")
        Toast.makeText(requireContext(),"OnViewCreated Called",Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG","OnStart Called: View Created")
        Toast.makeText(requireContext(),"OnStart Called",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG","OnResume Called: View Created")
        Toast.makeText(requireContext(),"OnResume Called",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG","OnPause Called: View Created")
        Toast.makeText(requireContext(),"OnPause Called",Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG","OnStop Called: View Created")
        Toast.makeText(requireContext(),"OnStop Called",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TAG","OnDestroyView Called: View Created")
        Toast.makeText(requireContext(),"OnDestroyView Called",Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("TAG","OnDetach Called: View Created")
        Toast.makeText(requireContext(),"OnDetach Called",Toast.LENGTH_SHORT).show()
    }


    }
