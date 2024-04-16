package com.example.a25fragmentlifecycle.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a25fragmentlifecycle.R

class BlankFragment1 : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onAttach() Called",Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onCreate() Called",Toast.LENGTH_SHORT).show()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank1, container, false)
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onCreateView() Called",Toast.LENGTH_SHORT).show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onActivityCreated() Called",Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onStart() Called",Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onResume() Called",Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onPause() Called",Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onStop() Called",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onDestroyView() Called",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onDestroy() Called",Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"Method Called")
        Toast.makeText(requireContext(),"onDetach() Called",Toast.LENGTH_SHORT).show()
    }

    companion object {

    }
}