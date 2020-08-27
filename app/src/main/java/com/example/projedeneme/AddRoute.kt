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
        val intent2 = Intent(this,AddRouteMap::class.java)
       // Log.e("log","lat-"+latitudeFrom.toString()+"lon-"+longitudeFrom.toString())
        intent2.putExtra("status","From")
        startActivity(intent2)
    }



        buttonTo.setOnClickListener {
           val intentTo = Intent(this, AddRouteMap::class.java)
            intentTo.putExtra("status","To")
            startActivity(intentTo)


           // textViewFrom.setText("lat-"+latitudeTo+"lon-"+longitudeTo)

        }
        buttonDrawRoute.setOnClickListener {
           intent=Intent(this,PolyActivity::class.java)
            startActivity(intent)

        }


    }



}