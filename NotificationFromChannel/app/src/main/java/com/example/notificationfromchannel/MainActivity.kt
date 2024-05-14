package com.example.notificationfromchannel

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notificationfromchannel.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.high.setOnClickListener {
            val highIntent = Intent(this, MyIntentService::class.java)
            highIntent.putExtra("Data", binding.mainDescription.text.toString()) // Add this line
            val highPendingIntent = PendingIntent.getService(this, 0, highIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            // Use highIntent instead of intent here
            highIntent.putExtra("Data",binding.mainDescription.text.toString())
//            Toast.makeText(this, "high", Toast.LENGTH_SHORT).show()
            var builder1 = NotificationCompat.Builder(this, App().CHANNEL_ID1)
            builder1.setSmallIcon(R.drawable.high)
            builder1.setContentTitle(binding.mainTitle.text.toString())
            builder1.setContentText(binding.mainDescription.text.toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(highPendingIntent) // when we click on notification
                .addAction(R.drawable.high,"Click Here",highPendingIntent) // when we click on button
                .addAction(R.drawable.high,"Click Here",highPendingIntent)
                .addAction(R.drawable.high,"Click Here",highPendingIntent)
                // Here we can only define three add action        .addAction(R.drawable.high,"Click Here",highPendingIntent)
                .setColor(Color.BLUE)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
            builder1.setPriority(NotificationCompat.PRIORITY_HIGH).build()
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(1, builder1.build())

        }

//        binding.low.setOnClickListener {
//            Toast.makeText(this, "low", Toast.LENGTH_SHORT).show()
//            var builder2 = NotificationCompat.Builder(this,App().CHANNEL_ID2 )
//                builder2.setSmallIcon(R.drawable.low)
//                builder2.setContentTitle(binding.mainTitle.text.toString())
//                builder2.setContentText(binding.mainDescription.text.toString())
//            builder2.setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
//
//            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//                manager.notify(2, builder2.build())
//
//
//        }
            binding.defaultBtn.setOnClickListener {
                Toast.makeText(this, "default", Toast.LENGTH_SHORT).show()
                var builder3 = NotificationCompat.Builder(this, App().CHANNEL_ID3)
                builder3.setSmallIcon(R.drawable.default_btn)
                builder3.setContentTitle(binding.mainTitle.text.toString())
                builder3.setContentText(binding.mainDescription.text.toString())
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setColor(Color.BLUE)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true)
                builder3.setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
                val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                manager.notify(3, builder3.build())

            }
            binding.low.setOnClickListener {
                Toast.makeText(this, "low", Toast.LENGTH_SHORT).show()
                var builder3 = NotificationCompat.Builder(this, App().CHANNEL_ID3)
                builder3.setSmallIcon(R.drawable.low)
                builder3.setContentTitle(binding.mainTitle.text.toString())
                builder3.setContentText(binding.mainDescription.text.toString())
                  .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setColor(Color.BLUE)
                    .setOnlyAlertOnce(true)
                    .setAutoCancel(true)
                builder3.setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
                val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                manager.notify(2, builder3.build())

            }

        }
    }
