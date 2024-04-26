package com.example.customtoast

import android.annotation.SuppressLint
import android.os.Bundle
import android.renderscript.Type
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.emreesen.sntoast.SnToast




class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button: Button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
//            FancyToast.makeText(this," Successfully !",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show()
            SnToast.Builder()
                .context(this@MainActivity)
                .type(com.emreesen.sntoast.Type.SUCCESS)
                .message("Success !")
                .iconSize(40)
                .textSize(20)
                .animation(true)
                .duration(3000)
                //.cancelable(false or true) Optional Default: False
                // .iconSize(int size) Optional Default: 34dp
                // .textSize(int size) Optional Default 18sp
                // .animation(false or true) Optional Default: True
                // .duration(int ms) Optional Default: 3000ms
                // .backgroundColor(R.color.example) Default: It is filled according to the toast type. If an assignment is made, the assigned value is used
                // .icon(R.drawable.example) Default: It is filled according to the toast type. If an assignment is made, the assigned value is used
                .build()



        }

    }

}