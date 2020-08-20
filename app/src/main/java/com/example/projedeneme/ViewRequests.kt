package com.example.projedeneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_deneme.*

class ViewRequests : AppCompatActivity(),Adapter.OnRideItemClickListener {

    var rideList: ArrayList<Ride> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deneme)
        fillData(rideList)

        var myAdapter = Adapter(rideList,this)
        recyclerView.adapter=myAdapter
        var linerLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linerLayoutManager

    }



    fun fillData(List : ArrayList<Ride>) : ArrayList<Ride>{
        val RideTemp = Ride(
            "1",
            "Keçiören",
            "Gölbaşı",
            "20.08.2020",
            "13.00"
        )
        for (i in 0 until 20)
        {
            List.add(RideTemp)
        }

            return List


    }

    override fun onItemClick(item: Ride, position: Int) {
        Toast.makeText(this,item.destination,Toast.LENGTH_SHORT).show()
    }
}