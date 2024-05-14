package com.example.notificationfromchannel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadcastReceivers :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,intent?.getStringExtra("Data"),Toast.LENGTH_SHORT).show()
    }
}