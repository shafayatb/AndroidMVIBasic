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

    override fun equals(other: Any?): Boolean {

        if(javaClass != other?.javaClass){
            return false
        }

        other as BlogPost

        if (pk != other.pk){
            return false
        }

        return true
    }

}