package com.baldystudios.androidmvibasic.api

import com.baldystudios.androidmvibasic.model.BlogPost
import com.baldystudios.androidmvibasic.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("placeholder/blogs")
    fun getBlogPosts(): List<BlogPost>


    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): User

}