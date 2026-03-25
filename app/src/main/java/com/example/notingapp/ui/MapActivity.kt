package com.example.notingapp.ui

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notingapp.R
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import java.util.*

class MapActivity : AppCompatActivity() {

    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().userAgentValue = packageName

        setContentView(R.layout.activity_map)

        map = findViewById(R.id.map)
        map.setMultiTouchControls(true)

        val startPoint = GeoPoint(10.762622, 106.660172)
        map.controller.setZoom(15.0)
        map.controller.setCenter(startPoint)

        // 🔥 LISTENER MỚI (THAY MapEventsReceiver cũ)
        map.addMapListener(object : MapListener {

            override fun onScroll(event: ScrollEvent?): Boolean {
                return false
            }

            override fun onZoom(event: ZoomEvent?): Boolean {
                return false
            }
        })

        // 🔥 BẮT CLICK MAP (CÁCH KHÁC - CHUẨN)
        map.setOnTouchListener { _, event ->

            if (event.action == android.view.MotionEvent.ACTION_UP) {

                val projection = map.projection
                val geoPoint = projection.fromPixels(
                    event.x.toInt(),
                    event.y.toInt()
                ) as GeoPoint

                map.overlays.clear()

                val marker = Marker(map)
                marker.position = geoPoint
                marker.title = "Selected Location"
                map.overlays.add(marker)

                val locationName = getLocationName(
                    geoPoint.latitude,
                    geoPoint.longitude
                )

                Toast.makeText(this, locationName, Toast.LENGTH_SHORT).show()

                val result = Intent()
                result.putExtra("lat", geoPoint.latitude)
                result.putExtra("lng", geoPoint.longitude)
                result.putExtra("locationName", locationName)

                setResult(RESULT_OK, result)
                finish()
            }

            false
        }
    }

    private fun getLocationName(lat: Double, lng: Double): String {
        return try {
            val geocoder = Geocoder(this, Locale.getDefault())

            @Suppress("DEPRECATION")
            val addresses = geocoder.getFromLocation(lat, lng, 1)

            if (!addresses.isNullOrEmpty()) {
                addresses[0].getAddressLine(0)
            } else {
                "Selected location"
            }
        } catch (_: Exception) {
            "Selected location"
        }
    }
}