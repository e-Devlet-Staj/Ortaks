package com.example.projedeneme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.ride_line_layout.view.*

class Adapter(val rideList:List<Ride>,var clickListener: OnRideItemClickListener) : RecyclerView.Adapter<Adapter.RideViewHolder>(){

    override fun getItemCount(): Int {
       return rideList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        var inflater= LayoutInflater.from(parent?.context)
        var rideLine=inflater.inflate(R.layout.ride_line_layout,parent,false)

        return RideViewHolder(rideLine)
    }



    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
       // holder?.rideDestination?.text=rideList.get(position).destination
       // holder?.rideFrom?.text=rideList.get(position).from
       // holder?.rideDate?.text=rideList.get(position).date
       // holder?.rideTime?.text=rideList.get(position).time
        holder.initialize(rideList.get(position),clickListener)

    }

    class RideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
            var rideLine = itemView as CardView
            var rideDestination=rideLine.destination_text
            var rideFrom=rideLine.from_text
            var rideDate=rideLine.date_text
            var rideTime= rideLine.time_text


            fun initialize(item: Ride,action:OnRideItemClickListener) {
                rideDestination.text=item.destination
                rideFrom.text=item.from
                rideDate.text=item.date
                rideTime.text=item.time

                itemView.setOnClickListener(){
                    action.onItemClick(item,adapterPosition)
                }

            }
    }

    interface OnRideItemClickListener{
        fun onItemClick(item :Ride,position:Int)
    }

}