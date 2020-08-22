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
        startActivityForResult(intent2,1)
    }
        val latitudeTempFrom= intent.extras?.getString("latitudeFrom").toString()
        val longitudeTempFrom= intent.extras?.getString("longitudeFrom").toString()
        if(latitudeTempFrom!=null){
            latitudeFrom=latitudeTempFrom
        }
        if(longitudeTempFrom!=null){
            longitudeFrom=longitudeTempFrom
        }



        buttonTo.setOnClickListener {
           val intentTo = Intent(this, AddRouteMap::class.java)
            intentTo.putExtra("status","To")
            startActivityForResult(intentTo,1)


           // textViewFrom.setText("lat-"+latitudeTo+"lon-"+longitudeTo)

        }
        val latitudeTempTo= intent.extras?.getString("latitudeTo").toString()
        val  longitudeTempTo= intent.extras?.getString("longitudeTo").toString()
        if(latitudeTempTo!=null){
            latitudeTo=latitudeTempTo
        }
        if(longitudeTempFrom!=null){
            longitudeTo=longitudeTempTo
        }
        buttonDrawRoute.setOnClickListener {


        }


    }



}