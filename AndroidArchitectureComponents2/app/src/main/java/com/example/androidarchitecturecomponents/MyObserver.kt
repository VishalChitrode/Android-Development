package com.example.androidarchitecturecomponents

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

class MyObserver : DefaultLifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Log.d("Observer", "Here we use onCreate")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Log.d("Observer","onStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.d("Observer","onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        Log.d("Observer","onPause")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        Log.d("Observer","onStop")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        Log.d("Observer","onDestroy")
    }
}

