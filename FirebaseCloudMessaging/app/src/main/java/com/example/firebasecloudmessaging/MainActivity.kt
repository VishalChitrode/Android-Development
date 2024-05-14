package com.example.firebasecloudmessaging

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasecloudmessaging.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var dataString:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        if (intent != null) {
//            if (intent.hasExtra("order_id")) {
//for (key in intent.extras!!.keySet()){
//    dataString = dataString+ intent.extras!!.getString(key)+"\n"
//}
//                binding.textView.text = dataString
//            }


        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful){
                    Log.d("MyTag",it.result.toString())
            }else{

            }
        }


//        binding.textView.setOnClickListener {
//
//        }
        }
    }
