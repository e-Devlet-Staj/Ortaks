package com.example.projedeneme.MapActivity

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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.projedeneme.R
import com.example.projedeneme.RequestActivity.AddRoute
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add_route_map.*
import java.util.*

class AddRouteMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    private val database = Firebase.database

    val PERMISSION_ID = 1010
    var options = MarkerOptions()
    var latLng = LatLng(2.3, 2.2)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_route_map)
        var newIntent = intent
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        Log.d("Debug:", CheckPermission().toString())
        Log.d("Debug:", isLocationEnabled().toString())
        RequestPermission()
        getLastLocation()

        buttonFinishSelection.setOnClickListener {
            var intent =Intent(this,
                AddRoute::class.java)

            Toast.makeText(this@AddRouteMap, newIntent.getStringExtra("status"),Toast.LENGTH_SHORT).show()
            if (newIntent.getStringExtra("status") == "From") {
                Toast.makeText(this@AddRouteMap, "From",Toast.LENGTH_SHORT).show()

              var tempLatLong= LatLong()
                tempLatLong.userId= FirebaseAuth.getInstance().currentUser?.uid.toString()
                tempLatLong.destination= ""
                tempLatLong.from=getCompleteAddressString(latLng.latitude,latLng.longitude)
                tempLatLong.latFrom=latLng.latitude.toString()
                tempLatLong.longFrom=latLng.longitude.toString()
                tempLatLong.longTo=""
                tempLatLong.latTo=""

                FirebaseDatabase.getInstance().reference
                    .child("latlong")
                    .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                    .setValue(tempLatLong)

            } else if (newIntent.getStringExtra("status") == "To") {
                Toast.makeText(this@AddRouteMap, "From",Toast.LENGTH_SHORT).show()
                FirebaseDatabase.getInstance().reference
                    .child("latlong")
                    .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                    .child("destination")
                    .setValue(getCompleteAddressString(latLng.latitude,latLng.longitude))

                FirebaseDatabase.getInstance().reference
                    .child("latlong")
                    .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                    .child("longTo")
                    .setValue(latLng.longitude.toString())

                FirebaseDatabase.getInstance().reference
                    .child("latlong")
                    .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                    .child("latTo")
                    .setValue(latLng.latitude.toString())

            }
            startActivity(intent)
            finish()
        }
        /*  latitudeFrom.addValueEventListener(object : ValueEventListener {
              override fun onDataChange(dataSnapshot: DataSnapshot) {
                  // This method is called once with the initial value and again
                  // whenever data at this location is updated.
                  val value = dataSnapshot.getValue<String>()
              }*/


        /*override fun onCancelled(error: DatabaseError) {
            // Failed to read value
            Log.w("kral", "Failed to read value.", error.toException())
        }
    })*/
    }


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.setOnMapClickListener(object : GoogleMap.OnMapClickListener {
            override fun onMapClick(latlng: LatLng) {
                // Clears the previously touched position
                mMap.clear();
                // Animating to the touched position
                latLng = LatLng(latlng.latitude, latlng.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


                mMap.addMarker(MarkerOptions().position(latLng))
            }
        })
        buttonSearch.setOnClickListener {
            val location = editTextMapSearchBar.text.toString()
            var addressList: List<Address>? = null

            if (location != "") {
                var geocoder = Geocoder(this)
                addressList = geocoder.getFromLocationName(location, 5)
                for (i in addressList!!.indices) {
                    var address = addressList[i]
                    latLng = LatLng(address.latitude, address.longitude)
                    options.position(latLng)
                    mMap.clear();
                    options.title("Aranan Konum")
                    options.icon(BitmapDescriptorFactory.fromResource(R.drawable.redloc))
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
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location: Location? = task.result
                    if (location == null) {
                        NewLocationData()
                    } else {
                        Log.d("Debug:", "Your Location:" + location.longitude)
                        latLng = LatLng(location.latitude, location.longitude)
                        options.position(latLng)
                        options.title("Bulunulan Konum")
                        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.loc))
                        mMap!!.addMarker(options)

                        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
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
            return
        }
        fusedLocationProviderClient!!.requestLocationUpdates(
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    private fun getCompleteAddressString(LATITUDE:Double, LONGITUDE:Double):String {
        var strAdd = ""
        val geocoder = Geocoder(this, Locale.getDefault())
        try
        {
            val addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
            if (addresses != null)
            {
                val returnedAddress = addresses.get(0)
                val strReturnedAddress = StringBuilder("")
                for (i in 0..returnedAddress.getMaxAddressLineIndex())
                {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
                }
                strAdd = strReturnedAddress.toString()
                Log.e("MMYCA", strReturnedAddress.toString())
            }
            else
            {
                Log.w("MYCMS", "No Address returned!")
            }
        }
        catch (e:Exception) {
            e.printStackTrace()
            Log.w("4wterg", "Canont get Address!")
        }
        return strAdd
    }
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {

            var lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:", "your last last location: " + lastLocation.longitude.toString())
        }
    }
}