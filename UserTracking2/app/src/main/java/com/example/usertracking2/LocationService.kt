package com.example.usertracking2

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.greenrobot.eventbus.EventBus

class LocationService : Service() {

    private lateinit var DbRef: DatabaseReference
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private lateinit var notificationManager: NotificationManager

    private var location: Location? = null

    companion object {
        const val CHANNEL_ID = "12345"
        const val NOTIFICATION_ID = 12345
    }

    override fun onCreate() {
        super.onCreate()
        DbRef = FirebaseDatabase.getInstance().reference
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create().apply {
            interval = 10000 // 10 seconds
            fastestInterval = 5000 // 5 seconds
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                onNewLocation(locationResult.lastLocation)
            }
        }
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Location Service"
            val descriptionText = "Foreground Location Service"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null
        )
    }

    private fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        startForeground(NOTIFICATION_ID, getNotification())
        startLocationUpdates()
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
//        stopForeground(true)
    }

    private fun onNewLocation(location: Location?) {
        this.location = location
        postLocationEvent(location)
        updateFirebaseDatabase()
        updateNotification()
    }

    private fun updateFirebaseDatabase() {
        location?.let {
            val locationData = hashMapOf("latitude" to it.latitude, "longitude" to it.longitude)
            DbRef.child("location").setValue(locationData)
        }
    }

    private fun getNotification(): Notification {
        val contentText = "Latitude: ${location?.latitude}, Longitude: ${location?.longitude}"
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Location Updates")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.baseline_my_location)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
    }

    private fun updateNotification() {
        val contentText = "Latitude: ${location?.latitude}, Longitude: ${location?.longitude}"
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Location Updates")
            .setContentText(contentText)
            .setSmallIcon(R.drawable.baseline_my_location)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun postLocationEvent(location: Location?) {
        EventBus.getDefault().post(LocationEvent(location?.latitude, location?.longitude))
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> feec63d52883b6e88af3bac23f832bfb5ab65885
