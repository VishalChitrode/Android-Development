package com.example.a31motiontoast

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        MotionToast.createToast(this,
//            "Hurray success 😍",
//            "Upload Completed successfully!",
//            MotionToastStyle.SUCCESS,
//            MotionToast.GRAVITY_BOTTOM,
//            MotionToast.LONG_DURATION,
//            ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold))
//
//
//        MotionToast.createToast(this,
//            "Failed ☹️",
//            "Profile Update Failed!",
//            MotionToastStyle.ERROR,
//            MotionToast.GRAVITY_BOTTOM,
//            MotionToast.LONG_DURATION,
//            ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold))
//
//        MotionToast.createToast(this,"Please fill all the details!",
//            "Failed ☹️",
//            MotionToastStyle.WARNING,
//            MotionToast.GRAVITY_BOTTOM,
//            MotionToast.LONG_DURATION,
//            ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold))
//
//        MotionToast.createToast(this,"This is information toast!",
//            "Failed ☹️",
//            MotionToastStyle.INFO,
//            MotionToast.GRAVITY_BOTTOM,
//            MotionToast.LONG_DURATION,
//            ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold))

        MotionToast.createToast(this,"Delete all history!",
            "Failed ☹️",
            MotionToastStyle.DELETE,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(this, www.sanju.motiontoast.R.font.helveticabold))





    }


}