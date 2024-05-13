package com.example.firebasestorage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firebasestorage.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.storage.storage
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private val binding  : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.uploadButton.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            imagelauncher.launch(intent)
        }
    }
    val imagelauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            if (it.data != null){
                val ref = Firebase.storage.reference.child("Photo/" +System.currentTimeMillis() + "." +getFileType(it.data!!.data!!))
//                val ref = Firebase.storage.reference.child("photo")
                ref.putFile(it.data!!.data!!).addOnCompleteListener {
                    ref.downloadUrl.addOnSuccessListener {
                        Firebase.database.reference.child("Photo").push().setValue(it.toString())
//                        binding.imageView.setImageURI(it)
                        Toast.makeText(this,"Photo uploaded",Toast.LENGTH_SHORT).show()
                        Picasso.get().load(it).into(binding.imageView);

                    }
                }
            }

        }else{

        }
    }

    private fun getFileType(data: Uri): String? {
val r  = contentResolver
        val mimeType = MimeTypeMap.getSingleton()
        return mimeType.getMimeTypeFromExtension(r.getType(data!!))



}}