package com.example.userlocationtracker

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.Manifest

class MainActivity : AppCompatActivity() {
    // Request code for location permission request
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
       databaseReference = FirebaseDatabase.getInstance().getReference()

        // Start the location tracking service
        startService(Intent(this, LocationTrackingService::class.java))
        // Initialize fusedLocationClient and databaseReference
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        databaseReference = FirebaseDatabase.getInstance().reference.child("locations")



        // Request location permission if not granted
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted, start location updates
            startLocationUpdates()
        }
        fun startLocationUpdates() {
            // Get the last known location
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        // Push the location data to Firebase
                        pushLocationToFirebase(location.latitude, location.longitude)
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("LocationTracking", "Error getting location: ${e.message}")
                }
        }

        fun pushLocationToFirebase(latitude: Double, longitude: Double) {
            // Create a LocationData object
            val locationData = LocationData(latitude, longitude)

            // Push the location data to Firebase Realtime Database
            databaseReference.push().setValue(locationData)
                .addOnSuccessListener {
                    Log.d("LocationTracking", "Location data added to Firebase")
                }
                .addOnFailureListener { e ->
                    Log.e("LocationTracking", "Error adding location to Firebase: ${e.message}")
                }
        }
    }
    private fun startLocationUpdates() {
        // Get the last known location
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    // Push the location data to Firebase
                    pushLocationToFirebase(location.latitude, location.longitude)
                }
            }
            .addOnFailureListener { e ->
                Log.e("LocationTracking", "Error getting location: ${e.message}")
            }
    }

    private fun pushLocationToFirebase(latitude: Double, longitude: Double) {
        // Create a LocationData object
        val locationData = LocationData(latitude, longitude)

        // Push the location data to Firebase Realtime Database
        databaseReference.push().setValue(locationData)
            .addOnSuccessListener {
                Log.d("LocationTracking", "Location data added to Firebase")
            }
            .addOnFailureListener { e ->
                Log.e("LocationTracking", "Error adding location to Firebase: ${e.message}")
            }
    }
}