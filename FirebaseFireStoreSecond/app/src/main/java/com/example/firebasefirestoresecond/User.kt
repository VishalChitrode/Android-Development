package com.example.firebasefirestoresecond

class User {
    var name:String? = null
    var password :String? = null
    var id:String? = null
    constructor() // primary construction because firebase need it
    constructor(name:String?,password:String?,id:String?){ // secondary construction
        this.name = name
        this.password = password
        this.id = id

    }
}