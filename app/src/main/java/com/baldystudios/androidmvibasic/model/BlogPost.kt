package com.baldystudios.androidmvibasic.model

data class BlogPost(

    val pk: Int? = null,
    val title: String? = null,
    val body: String? = null,
    val image: String? = null

){

    override fun toString(): String {
        return "BlogPost(pk=$pk, title=$title, body=$body, image=$image)"
    }
}