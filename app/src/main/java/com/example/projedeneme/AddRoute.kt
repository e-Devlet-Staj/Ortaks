package com.example.projedeneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< Updated upstream
=======
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_add_route.*
>>>>>>> Stashed changes

class AddRoute : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_route)
<<<<<<< Updated upstream
=======

    buttonFrom.setOnClickListener {
        val intent2 = Intent(this,AddRouteMap::class.java)
        intent2.putExtra("status","From")
        startActivity(intent2)
    }



        buttonTo.setOnClickListener {
           val intentTo = Intent(this, AddRouteMap::class.java)
            intentTo.putExtra("status","To")
            startActivity(intentTo)
        }
        butAccept.setOnClickListener {
            var tempRide= Ride()
            tempRide.userId= FirebaseAuth.getInstance().currentUser?.uid.toString()
            tempRide.destination= textViewTo.text as String
            tempRide.from=textViewFrom.text as String
            tempRide.date=calendarView.date.toString()
            tempRide.time=eTtime.text.toString()
            
        }


>>>>>>> Stashed changes
    }
}