package com.example.projedeneme

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_add_route.*
import kotlinx.android.synthetic.main.activity_add_route_map.*

class AddRouteMap : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    val PERMISSION_ID = 1010
    var options = MarkerOptions()
    var latLng = LatLng(2.3, 2.2)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_route_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        Log.d("Debug:", CheckPermission().toString())
        Log.d("Debug:", isLocationEnabled().toString())
        RequestPermission()
        getLastLocation()
        buttonFinishSelection.setOnClickListener {
            var intent = Intent(this, AddRoute::class.java)
            intent.putExtra("latitude", "" + latLng.latitude)
            intent.putExtra("longitude", "" + latLng.longitude)
            startActivity(intent)
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener(object : GoogleMap.OnMapClickListener {
            override fun onMapClick(latlng: LatLng) {
                // Clears the previously touched position
                mMap.clear();
                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                val touchedLocation = LatLng(latlng.latitude, latlng.longitude)
                mMap.addMarker(MarkerOptions().position(touchedLocation))
            }
        })
        buttonSearch.setOnClickListener {
            var location = editTextMapSearchBar.text.toString()
            var addressList: List<Address>? = null

            if (location != "") {
                var geocoder = Geocoder(this)
                addressList = geocoder.getFromLocationName(location, 5)
                for (i in addressList!!.indices) {
                    var address = addressList[i]
                    latLng = LatLng(address.latitude, address.longitude)
                    options.position(latLng)
                    options.title("Aranan Konum")
                    mMap!!.addMarker(options)
                    mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                }

            }
        }

    }

    private fun CheckPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun RequestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), PERMISSION_ID
        )
    }

    fun isLocationEnabled(): Boolean {
        //this function will return to us the state of the location service
        //if the gps or the network provider is enabled then it will return true otherwise it will return false
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug:", "You have the Permission")
            }
        }
    }

    fun getLastLocation() {
        if (CheckPermission()) {
            if (isLocationEnabled()) {
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
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        NewLocationData()
                    } else {
                        Log.d("Debug:", "Your Location:" + location.longitude)
                        var latLng = LatLng(location.latitude, location.longitude)
                        options.position(latLng)
                        options.title("Aranan Konum")
                        mMap!!.addMarker(options)
                        mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                    }
                }
            }
        } else {
            Toast.makeText(this, "Please Turn on Your device Location", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun NewLocationData() {
        var locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
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
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }


    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {

            var lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:", "your last last location: " + lastLocation.longitude.toString())
        }
    }
}