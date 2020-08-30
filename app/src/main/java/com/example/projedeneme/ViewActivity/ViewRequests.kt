package com.example.projedeneme.ViewActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projedeneme.R
import com.example.projedeneme.RequestActivity.AddRoute
import com.example.projedeneme.RequestActivity.Ride
import com.example.projedeneme.UserActivity.UserActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_deneme.*


class ViewRequests : AppCompatActivity() {
    var rideList: ArrayList<Ride> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deneme)

      //  fillData(rideList)
        var ref = FirebaseDatabase.getInstance().reference
        var user= FirebaseAuth.getInstance().currentUser
        var sorgu=ref.child("ride")
            .orderByKey()
        sorgu.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {

                for (singleSnapshot in p0!!.children){
                    var tempRide=singleSnapshot.getValue(Ride::class.java)
                    var temp= Ride()
                    temp.userId=tempRide?.userId
                    temp.destination=tempRide?.destination
                    temp.from=tempRide?.from
                    temp.date=tempRide?.date
                    temp.time=tempRide?.time
                    Log.e("Firebase","userid:"+tempRide?.userId+"destination :"+tempRide?.destination+"from :"+tempRide?.from)
                    rideList.add(temp)
                    Log.e("Ffffff","userid:"+rideList.get(0).userId+"destination :"+rideList.get(0).destination+"from :"+rideList.get(0).from)
                }
                Log.e("Ffffff","userid:"+rideList.get(0).userId+"destination :"+rideList.get(0).destination+"from :"+rideList.get(0).from)
                Log.e("Ffffff","userid:"+rideList.get(1).userId+"destination :"+rideList.get(1).destination+"from :"+rideList.get(1).from)
                var myAdapter = Adapter(rideList)
                recyclerView.adapter = myAdapter

            }

        })


        var linerLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.layoutManager = linerLayoutManager

        imageView5.setOnClickListener{
             intent = Intent(this, UserActivity::class.java)
                startActivity(intent)
    }
        buttonAdd.setOnClickListener {
            intent = Intent(this, AddRoute::class.java)
            startActivity(intent)
        }


        imageView4.setOnClickListener(){
            finish()
            startActivity(getIntent())
        }
    }

    fun fillData(List: ArrayList<Ride>): ArrayList<Ride> {
        var ref = FirebaseDatabase.getInstance().reference
        var user= FirebaseAuth.getInstance().currentUser
        var sorgu=ref.child("ride")
            .orderByKey()
        sorgu.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for (singleSnapshot in p0!!.children){
                    var tempRide=singleSnapshot.getValue(Ride::class.java)
                    var temp= Ride()
                    temp.userId=tempRide?.userId
                    temp.destination=tempRide?.destination
                    temp.from=tempRide?.from
                    temp.date=tempRide?.date
                    temp.time=tempRide?.time
                Log.e("Firebase","userid:"+tempRide?.userId+"destination :"+tempRide?.destination+"from :"+tempRide?.from)
                    List.add(temp)
                    Log.e("Ffffff","userid:"+List.get(0).userId+"destination :"+List.get(0).destination+"from :"+List.get(0).from)
                }

            }

        })



        return List


    }

}