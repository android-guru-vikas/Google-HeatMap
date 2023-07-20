package com.awesome.android.demoheatmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.awesome.android.demoheatmap.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        googleMap.setMaxZoomPreference(20f)
        googleMap.setMinZoomPreference(1f)
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        val patternItems = listOf(Dot(), Gap(10f), Dash(20f))

        val outerRectangleOptions = PolygonOptions()
            .add(
                LatLng(20.953999, 79.444065),
                LatLng(20.954141, 79.442888),
                LatLng(20.953436, 79.442667),
                LatLng(20.953257, 79.443890),
            )
            .zIndex(60f)
            .strokeWidth(2f)
            .strokeJointType(JointType.BEVEL)
            .strokePattern(patternItems)
            .strokeColor(0x7d800000)
            .fillColor(0x7dff0000) // Red with 50% opacity
        googleMap.addPolygon(outerRectangleOptions)

        // Draw Inner Rectangle (Green) inside Outer Rectangle
        val innerRectangleOptions = PolygonOptions()
            .add(
                LatLng(20.953914, 79.443840),
                LatLng(20.954007, 79.443070),
                LatLng(20.953666, 79.443022),
                LatLng(20.953538, 79.443800),
            )
            .strokeJointType(JointType.BEVEL)
            .strokeColor(0x7d800000)
            .strokeWidth(2f)
            .zIndex(80f)
            .strokePattern(patternItems)
            .fillColor(0x7dffc007) // Green with 50% opacity
        googleMap.addPolygon(innerRectangleOptions)

        // Draw Smaller Rectangle (Yellow) inside Inner Rectangle
        val smallerRectangleOptions = PolygonOptions()
            .add(
                LatLng(20.953811, 79.443684),
                LatLng(20.953854, 79.443290),
                LatLng(20.953701, 79.443277),
                LatLng(20.953659, 79.443649),
            )
            .strokeJointType(JointType.BEVEL)
            .zIndex(100f)
            .strokeWidth(2f)
            .strokeColor(0x7d800000)
            .strokePattern(patternItems)
            .fillColor(0x7d18974E) // Yellow with 50% opacity
        googleMap.addPolygon(smallerRectangleOptions)

        val centerOfBihar = LatLng(20.953782, 79.443418)
        val cameraUpdate =
            CameraUpdateFactory.newLatLngZoom(centerOfBihar, 18f) // Adjust zoom level as needed
        googleMap.moveCamera(cameraUpdate)

    }
}