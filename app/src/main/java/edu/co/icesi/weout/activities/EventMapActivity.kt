package edu.co.icesi.weout.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import edu.co.icesi.weout.R
import edu.co.icesi.weout.databinding.ActivityEventMapBinding

class EventMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityEventMapBinding

    private lateinit var marker: Marker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEventMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setBttn.setOnClickListener{

            val sp = getSharedPreferences("coords", MODE_PRIVATE)
            sp.edit().putString("lat", "${this.marker.position.latitude}").apply()
            sp.edit().putString("lng", "${this.marker.position.longitude}").apply()

            finish()
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setOnMapClickListener(OnMapClickListener { latLng ->

            val markerOptions = MarkerOptions()
            markerOptions.position(latLng)

            // Clears the previously touched position
            mMap.clear()

            // Animating to the touched position
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))

            // Placing a marker on the touched position

            this.marker = this.putNewMarker(latLng.latitude, latLng.longitude)

            mMap.addMarker(markerOptions)

        })

        this.putNewMarker(3.4, -72.0)
    }

    private fun putNewMarker(lat: Double, lng: Double): Marker {
        // Add a marker in Sydney and move the camera
        val pos = LatLng(lat, lng)
        val marker = mMap.addMarker(MarkerOptions().position(pos))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos))

        return marker
    }
}