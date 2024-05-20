package com.example.usertracking2

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.usertracking2.databinding.ActivityMainBinding
import com.google.firebase.database.FirebaseDatabase
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!
    private var service: Intent? = null
    private val backgroundLocation =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {

            }
        }
    private val locationPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            when {
                it.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        if (ActivityCompat.checkSelfPermission(
                                this,
                                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            backgroundLocation.launch(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                        }
                    }

                }

                it.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        service = Intent(this, LocationService::class.java)

        binding.apply {
            btnStartLocationTracking.setOnClickListener {
                checkPermissions()
            }
//            btnRemoveLocationTracking.setOnClickListener {
//                stopService(service)
//            }
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLocationEvent(event: LocationEvent) {
        event.latitude?.let { latitude ->
            event.longitude?.let { longitude ->
                // Send location updates to Firebase Realtime Database
                val locationRef = FirebaseDatabase.getInstance().getReference("location")
                locationRef.setValue(hashMapOf("latitude" to latitude, "longitude" to longitude))
            }
        }
    }
    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }
    fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                locationPermissions.launch(
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            } else {
                startService(service)
            }
        }
    }
    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
    override fun onDestroy() {
        super.onDestroy()
        stopService(service)
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
    @Subscribe
    fun receiveLocationEvent(locationEvent: LocationEvent) {
        binding.tvLatitude.text = "Latitude -> ${locationEvent.latitude}"
        binding.tvLongitude.text = "Longitude -> ${locationEvent.longitude}"
    }
}