package com.example.projedeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_route.*

class AddRoute : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_route)
    buttonFrom.setOnClickListener {
        val intentFrom = Intent(this, AddRouteMap::class.java)
        startActivity(intentFrom)
    }
        this.intent =intent
        var latitude= intent.extras?.getString("latitude").toString()
        var longitude= intent.extras?.getString("longitude").toString()
        buttonTo.setOnClickListener {
          Log.e("log","lat-"+latitude.toString()+"lon-"+longitude.toString())
            textViewFrom.setText("lat-"+latitude+"lon-"+longitude)
        }
        buttonDrawRoute.setOnClickListener {


        }

    }



}