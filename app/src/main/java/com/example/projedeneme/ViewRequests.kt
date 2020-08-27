package com.example.projedeneme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_deneme.*

class ViewRequests : AppCompatActivity() {
    var rideList: ArrayList<Ride> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deneme)
        fillData(rideList)

        var myAdapter = Adapter(rideList)
        recyclerView.adapter = myAdapter
        var linerLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linerLayoutManager


        buttonAdd.setOnClickListener {
            intent = Intent(this, AddRoute::class.java)
            startActivity(intent)
        }
    }

    fun fillData(List: ArrayList<Ride>): ArrayList<Ride> {
        val RideTemp = Ride(
            "1",
            "Keçiören",
            "Gölbaşı",
            "20.08.2020",
            "13.00"
        )
        for (i in 0 until 20) {
            List.add(RideTemp)
        }

        return List


    }

}