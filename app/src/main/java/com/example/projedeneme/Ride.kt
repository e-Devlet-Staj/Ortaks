package com.example.projedeneme

import com.google.android.gms.maps.model.LatLng
import java.lang.reflect.Constructor

class Ride {

    var userId: String? =null
    var destination: String? =null
    var from: String? =null
    var date: String? =null
    var time: String? =null
    constructor(
        userId: String?,
        destination: String?,
        from: String?,
        date: String?,
        time: String?
    ) {
        this.userId = userId
        this.destination = destination
        this.from = from
        this.date = date
        this.time = time
    }
    constructor()


}
