package com.baldystudios.androidmvibasic.model

data class User(

    val email: String? = null,
    val username:String? = null,
    val image: String? = null

) {

    override fun toString(): String {
        return "User(email=$email, username=$username, image=$image)"
    }
}