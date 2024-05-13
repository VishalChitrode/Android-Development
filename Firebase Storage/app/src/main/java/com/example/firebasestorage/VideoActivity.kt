package com.example.firebasestorage

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.firebasestorage.databinding.ActivityVideoBinding
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.storage.storage
import com.squareup.picasso.Picasso

class VideoActivity : AppCompatActivity() {
    private val binding: ActivityVideoBinding by lazy {
        ActivityVideoBinding.inflate(layoutInflater)
    }
    private lateinit var progessDialog: ProgressDialog
private lateinit var mediaController: MediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.videoView.isVisible = false
        progessDialog = ProgressDialog(this@VideoActivity)
        binding.videoButton.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "video/*"
            Videolauncher.launch(intent)
        }


    }

    val Videolauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                if (it.data != null) {
                    progessDialog.setTitle("Uploading Video")
                    progessDialog.show()
                    

                    val ref = Firebase.storage.reference.child("video/" +System.currentTimeMillis() + "." +getFileType(it.data!!.data!!))

                    ref.putFile(it.data!!.data!!).addOnCompleteListener {
                        ref.downloadUrl.addOnSuccessListener {
                            Firebase.database.reference.child("video").push().setValue(it.toString())
                            progessDialog.dismiss()
//                        binding.imageView.setImageURI(it)
                            Toast.makeText(this,"Video uploaded",Toast.LENGTH_SHORT).show()
                            binding.videoView.isVisible = false
                    val mediaController = MediaController(this@VideoActivity)
                    mediaController.setAnchorView(binding.videoView)
                    binding.videoView.isVisible = true
                    binding.videoView.setVideoURI(it)
                    binding.videoView.setMediaController(mediaController)
                    binding.videoView.start()
                            binding.videoView.setOnCompletionListener {
                                ref.delete().addOnSuccessListener {
                                    Toast.makeText(this,"Video Deleted",Toast.LENGTH_SHORT).show()
                                }
                            }


                        }
                    }
                        .addOnProgressListener {
                            val value = 100.0 * (it.bytesTransferred / it.totalByteCount)
                            progessDialog.setTitle("Uploaded " + value.toString() + "%")
                        }

//binding.videoView.isVisible = false
//                    val mediaController = MediaController(this@VideoActivity)
//                    mediaController.setAnchorView(binding.videoView)
//                    binding.videoView.isVisible = true
//                    binding.videoView.setVideoURI(it.data!!.data)
//                    binding.videoView.setMediaController(mediaController)
//                    binding.videoView.start()
                }
            }


}



    private fun getFileType(data: Uri): String? {
        val r  = contentResolver
        val mimeType = MimeTypeMap.getSingleton()
        return mimeType.getMimeTypeFromExtension(r.getType(data!!))



    }
}
