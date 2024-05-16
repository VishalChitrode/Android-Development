package com.example.mapboxmap

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mapbox.android.gestures.RotateGestureDetector
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.image.image
import com.mapbox.maps.extension.style.layers.generated.symbolLayer
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.OnRotateListener


class MainActivity : AppCompatActivity(){

    private lateinit var mapView: MapView
    @SuppressLint("MissingInflatedId")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mapView = findViewById(R.id.mapView)
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS,
            object : Style.OnStyleLoaded {
                override fun onStyleLoaded(style: Style) {
                    addAnnotationToMap()
                }
            })
        // set initial camera position
        val initialCameraOptions = CameraOptions.Builder()
            .center(Point.fromLngLat(75.8577, 22.7196))
            .pitch(45.0)
            .zoom(15.5)
            .bearing(-17.6)
            .build()

        mapView.mapboxMap.also {
            it.setCamera(
                CameraOptions.Builder()
                    .center(Point.fromLngLat(LONGITUDE, LATITUDE))
                    .zoom(8.0)
                    .build()
            )
        }.loadStyle(
            styleExtension = style(Style.MAPBOX_STREETS) {
                // prepare blue marker from resources
                +image(
                    BLUE_ICON_ID,
                    ContextCompat.getDrawable(this@MainActivity, R.drawable.img)!!.toBitmap()
                )
                +geoJsonSource(SOURCE_ID) {
                    geometry(Point.fromLngLat(LONGITUDE, LATITUDE))
                }
                +symbolLayer(LAYER_ID, SOURCE_ID) {
                    iconImage(BLUE_ICON_ID)
                    iconAnchor(IconAnchor.BOTTOM)
                }
            }
        )
    }

    companion object {
        private const val BLUE_ICON_ID = "blue"
        private const val SOURCE_ID = "source_id"
        private const val LAYER_ID = "layer_id"
        private const val LATITUDE = 22.719568
        private const val LONGITUDE = 75.857727
    }

    private fun addAnnotationToMap() {
        bitmapFromDrawableRes(
            this@MainActivity,
            R.drawable.img
        )?.let {
            val annotationApi = mapView?.annotations
            val pointAnnotationManager = annotationApi?.createPointAnnotationManager()
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                .withPoint(Point.fromLngLat(75.857727, 22.719568))
                .withIconImage(it)
            pointAnnotationManager?.create(pointAnnotationOptions)
        }
    }
    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }
}








