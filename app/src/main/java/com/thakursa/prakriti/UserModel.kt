package com.thakursa.prakriti

class UserModel {
    lateinit var name:String
    lateinit var number:String
    lateinit var email:String

    constructor(name: String, number: String, email: String) {
        this.name = name
        this.number = number
        this.email = email
    }
}