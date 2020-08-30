package com.example.projedeneme.MapActivity

class LatLong {
    var userId: String?=null
    var destination: String?=null
    var from: String?=null
    var latFrom: String?=null
    var latTo: String?=null
    var longFrom: String?=null
    var longTo: String?=null

    constructor(
        userId: String?,
        destination: String?,
        from: String?,
        latFrom: String?,
        latTo: String?,
        longFrom: String?,
        longTo: String?
    ) {
        this.userId = userId
        this.destination = destination
        this.from = from
        this.latFrom = latFrom
        this.latTo = latTo
        this.longFrom = longFrom
        this.longTo = longTo
    }

    constructor()

}