package com.example.gpstracking

import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.Manifest

class LocationTrackingService : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var DbRef: DatabaseReference
    private lateinit var auth : FirebaseAuth

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        DbRef = FirebaseDatabase.getInstance().reference
        startLocationUpdates()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback = object : LocationCallback() {
            val currentuser = FirebaseAuth.getInstance().currentUser
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.lastLocation?.let { location ->
                    auth.currentUser?.let { user ->
                        val locationKey = DbRef.child("users").child(user.uid).child("locations").push().key
                        val locationData = LocationData(location.latitude, location.longitude)
                        locationKey?.let { key ->
                            DbRef.child("users").child(user.uid).child("locations").child(key)
                                .setValue(locationData)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d("LocationTracking", "Location data added to Firebase")
                                    } else {
                                        Log.e("LocationTracking", "Error adding location to Firebase: ${task.exception}")
                                    }
                                }
                        }
                    }
                    storeLocationData(location)
                }
            }
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )
        }
    }
    private fun storeLocationData(location: Location) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.let { user ->
            val locationData = LocationData(location.latitude, location.longitude)
            val locationRef = DbRef.child("users").child(user.uid).child("locations").push()
            locationRef.setValue(locationData)
                .addOnSuccessListener {
                    Log.d("LocationTracking", "Location data added to Firebase")
                }
                .addOnFailureListener { e ->
                    Log.e("LocationTracking", "Error adding location to Firebase: ${e.message}")
                }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}
data class LocationData(val latitude: Double, val longitude: Double)
