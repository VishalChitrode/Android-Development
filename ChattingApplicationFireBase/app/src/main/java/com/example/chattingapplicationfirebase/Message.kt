package com.example.chattingapplicationfirebase

class Message {
    var message:String? = null
    var senderemail:String? = null

    constructor(){}
    constructor(message:String?, senderEmail:String){
        this.message = message
        this.senderemail = senderEmail
    }
}