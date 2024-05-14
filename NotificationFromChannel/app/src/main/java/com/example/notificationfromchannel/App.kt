package com.example.notificationfromchannel

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App :Application(){

    final public val CHANNEL_ID1 = "CHANNEL_ID1"
    final public val CHANNEL_ID2 = "CHANNEL_ID2"
    final public val CHANNEL_ID3 = "CHANNEL_ID3"

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            val channel1 = NotificationChannel(CHANNEL_ID1,"channel1", NotificationManager.IMPORTANCE_HIGH)
            channel1.description = "this is High channel1"
            val channel2 = NotificationChannel(CHANNEL_ID2,"channel2",NotificationManager.IMPORTANCE_DEFAULT)
            channel2.description = "this is Default channel2"
            val channel3 = NotificationChannel(CHANNEL_ID3,"channel3",NotificationManager.IMPORTANCE_DEFAULT)
            channel3.description = "this is Low channel3"

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)
            manager.createNotificationChannel(channel3)

        }
    }
}