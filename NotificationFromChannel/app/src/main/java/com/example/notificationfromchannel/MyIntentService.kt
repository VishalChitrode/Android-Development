package com.example.notificationfromchannel

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null){
            Log.d("MYTAG",intent.getStringExtra("Data")!!)
        }

    }

    private fun handleActionFoo(param1: String?, param2: String?) {
        TODO("Handle action Foo")
    }

    private fun handleActionBaz(param1: String?, param2: String?) {
        TODO("Handle action Baz")
    }

    companion object {

}}