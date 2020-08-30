package com.example.projedeneme.RequestActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projedeneme.MapActivity.LatLong
import com.example.projedeneme.MapActivity.AddRouteMap
import com.example.projedeneme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_add_route.*
import java.text.DateFormat
import java.util.*

class AddRoute : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_route)
        val calendar = Calendar.getInstance()
        var formattedDate : String = ""
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // set the calendar date as calendar view selected date
            calendar.set(year,month,dayOfMonth)

            // format the calendar selected date
            val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
            formattedDate = dateFormatter.format(calendar.time)

            // show calendar view selected date on text view
            // medium format date
        }



        var ref = FirebaseDatabase.getInstance().reference
        var user=FirebaseAuth.getInstance().currentUser

        var sorgu=ref.child("latlong")
            .orderByKey()
            .equalTo(user?.uid)

        sorgu.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (singleSnapshot in p0!!.children){
                    var tempLatLong=singleSnapshot.getValue(LatLong::class.java)
                    textViewFrom.text=tempLatLong?.from.toString()
                    textViewTo.text=tempLatLong?.destination.toString()
                }

            }

        })
        buttonFrom.setOnClickListener {v->
            var intent =Intent(v.context,
                AddRouteMap::class.java)
            intent.putExtra("status","From")

            v.context.startActivity(intent)
        }

        buttonTo.setOnClickListener {v->
            var intent =Intent(v.context,
                AddRouteMap::class.java)
            intent.putExtra("status","To")

            v.context.startActivity(intent)

        }

        butAccept.setOnClickListener {

            var tempRide= Ride()

            tempRide.userId= FirebaseAuth.getInstance().currentUser?.uid.toString()
            tempRide.destination= textViewTo.text as String
            tempRide.from=textViewFrom.text as String
            tempRide.date=formattedDate
            tempRide.time=eTtime.text.toString()

            FirebaseDatabase.getInstance().reference
                .child("ride")
                .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .setValue(tempRide)

        }


    }
}