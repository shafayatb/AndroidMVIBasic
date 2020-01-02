package com.baldystudios.androidmvibasic.api

import androidx.lifecycle.LiveData
import com.baldystudios.androidmvibasic.model.BlogPost
import com.baldystudios.androidmvibasic.model.User
import com.baldystudios.androidmvibasic.util.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("placeholder/blogs")
    fun getBlogPosts(): LiveData<GenericApiResponse<List<BlogPost>>>


    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): LiveData<GenericApiResponse<User>>

}