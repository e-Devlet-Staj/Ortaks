package com.example.projedeneme.UserActivity

class User {

    var username:String?=null
    var surname:String?=null
    var phone:String?=null
    var rate:String?=null
    var profilePhoto:String?=null
    var user_id:String?=null

    constructor(username: String, surname: String, phone: String, rate: String, profilePhoto: String, user_id: String) {
        this.username = username
        this.surname = surname
        this.phone = phone
        this.rate = rate
        this.profilePhoto = profilePhoto
        this.user_id = user_id
    }
    constructor()
}