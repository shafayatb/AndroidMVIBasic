package com.baldystudios.androidmvibasic.api

import com.baldystudios.androidmvibasic.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    const val BASE_URL = "https://open-api.xyz/"

    val retrofitBuilder: Retrofit.Builder by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())

    }

    val apiService: ApiService by lazy {
        retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }

}