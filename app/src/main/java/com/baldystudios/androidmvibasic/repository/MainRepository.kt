package com.baldystudios.androidmvibasic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.baldystudios.androidmvibasic.api.RetrofitBuilder
import com.baldystudios.androidmvibasic.ui.main.state.MainViewState
import com.baldystudios.androidmvibasic.util.ApiEmptyResponse
import com.baldystudios.androidmvibasic.util.ApiErrorResponse
import com.baldystudios.androidmvibasic.util.ApiSuccessResponse
import com.baldystudios.androidmvibasic.util.DataState

object MainRepository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {

        return Transformations
            .switchMap(RetrofitBuilder.apiService.getBlogPosts()) { apiResponse ->

                object : LiveData<DataState<MainViewState>>() {
                    override fun onActive() {
                        super.onActive()
                        when (apiResponse) {

                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    data = MainViewState(
                                        blogPosts = apiResponse.body
                                    )
                                )
                            }

                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    message = apiResponse.errorMessage
                                )
                            }

                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    message = "HTTP 204. Returned Nothing!"
                                )
                            }

                        }
                    }
                }
            }
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {

        return Transformations
            .switchMap(RetrofitBuilder.apiService.getUser(userId)) { apiResponse ->

                object : LiveData<DataState<MainViewState>>() {
                    override fun onActive() {
                        super.onActive()
                        when (apiResponse) {

                            is ApiSuccessResponse -> {
                                value = DataState.data(
                                    data = MainViewState(
                                        user = apiResponse.body
                                    )
                                )
                            }

                            is ApiErrorResponse -> {
                                value = DataState.error(
                                    message = apiResponse.errorMessage
                                )
                            }

                            is ApiEmptyResponse -> {
                                value = DataState.error(
                                    message = "HTTP 204. Returned Nothing!"
                                )
                            }

                        }
                    }
                }
            }
    }

}