package com.baldystudios.androidmvibasic.repository

import androidx.lifecycle.LiveData
import com.baldystudios.androidmvibasic.api.RetrofitBuilder
import com.baldystudios.androidmvibasic.model.BlogPost
import com.baldystudios.androidmvibasic.model.User
import com.baldystudios.androidmvibasic.ui.main.state.MainViewState
import com.baldystudios.androidmvibasic.util.ApiSuccessResponse
import com.baldystudios.androidmvibasic.util.DataState
import com.baldystudios.androidmvibasic.util.GenericApiResponse

object MainRepository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {

        return object : NetworkBoundResource<List<BlogPost>, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {

                result.value = DataState.data(
                    data = MainViewState(
                        blogPosts = response.body
                    )
                )

            }

            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {

                return RetrofitBuilder.apiService.getBlogPosts()

            }

        }.asLiveData()

    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {

        return object : NetworkBoundResource<User, MainViewState>() {

            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {

                result.value = DataState.data(
                    data = MainViewState(
                        user = response.body
                    )
                )

            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {

                return RetrofitBuilder.apiService.getUser(userId)

            }

        }.asLiveData()

    }

}