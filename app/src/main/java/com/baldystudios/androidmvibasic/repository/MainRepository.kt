package com.baldystudios.androidmvibasic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.baldystudios.androidmvibasic.api.RetrofitBuilder
import com.baldystudios.androidmvibasic.ui.main.state.MainViewState
import com.baldystudios.androidmvibasic.util.ApiEmptyResponse
import com.baldystudios.androidmvibasic.util.ApiErrorResponse
import com.baldystudios.androidmvibasic.util.ApiSuccessResponse

object MainRepository {

    fun getBlogPosts(): LiveData<MainViewState> {

        return Transformations
            .switchMap(RetrofitBuilder.apiService.getBlogPosts()) { apiResponse ->

                object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        when (apiResponse) {

                            is ApiSuccessResponse -> {
                                value = MainViewState(
                                    blogPosts = apiResponse.body
                                )
                            }

                            is ApiErrorResponse -> {
                                value = MainViewState()
                            }

                            is ApiEmptyResponse -> {
                                value = MainViewState()
                            }

                        }
                    }
                }
            }
    }

    fun getUser(userId: String): LiveData<MainViewState> {

        return Transformations
            .switchMap(RetrofitBuilder.apiService.getUser(userId)) { apiResponse ->

                object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        when (apiResponse) {

                            is ApiSuccessResponse -> {
                                value = MainViewState(
                                    user = apiResponse.body
                                )
                            }

                            is ApiErrorResponse -> {
                                value = MainViewState()
                            }

                            is ApiEmptyResponse -> {
                                value = MainViewState()
                            }

                        }
                    }
                }
            }
    }

}