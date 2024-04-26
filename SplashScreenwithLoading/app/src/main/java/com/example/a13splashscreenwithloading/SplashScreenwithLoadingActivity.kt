package com.example.a13splashscreenwithloading

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.lang.Exception

class SplashScreenwithLoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screenwith_loading)
        heavyTask();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    private fun heavyTask(){
LongOperation().execute()
    }
private open inner class  LongOperation : AsyncTask<String?,Void?, String?>(){
    override fun doInBackground(vararg params: String?): String? {
        for (i in 0..6) {
            try {
                Thread.sleep(1000)
            }
            catch (e: Exception) {
                Thread.interrupted()
            }
        }
        return "Result"
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        val intent = Intent(this@SplashScreenwithLoadingActivity,MainActivity::class.java)
        startActivity(intent)
        finish() // it is used to show splash screen only once
    }

}
}
