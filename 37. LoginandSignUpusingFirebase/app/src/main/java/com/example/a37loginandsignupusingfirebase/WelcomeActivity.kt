package com.example.a37loginandsignupusingfirebase

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.a37loginandsignupusingfirebase.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private  val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
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
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
            finish() // to show only splash screen once
        },2000)




        val welcomeText = "Welcome"
        val spannableText = SpannableString(welcomeText)
        spannableText.setSpan(ForegroundColorSpan(Color.parseColor("#FF0000")),0,5,0)
        spannableText.setSpan(ForegroundColorSpan(Color.parseColor("#000000")),5,welcomeText.length,0)
        binding.textView.text = spannableText

//            .text: This is a property of the TextView class in Android, used to set or get the text displayed by the TextView.
//        spannableText: This is the SpannableString object created earlier in the code, which contains the text "Welcome" with specific spans to apply different foreground colors to different parts of the text.
//

    }
}