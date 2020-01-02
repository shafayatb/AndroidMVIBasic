package com.baldystudios.androidmvibasic.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.baldystudios.androidmvibasic.model.BlogPost
import com.baldystudios.androidmvibasic.model.User
import com.baldystudios.androidmvibasic.ui.main.state.MainStateEvent
import com.baldystudios.androidmvibasic.ui.main.state.MainStateEvent.*
import com.baldystudios.androidmvibasic.ui.main.state.MainViewState
import com.baldystudios.androidmvibasic.util.AbsentLiveData

class MainViewModel : ViewModel() {

    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent) { stateEvent ->

            stateEvent?.let {
                handleStateEvent(it)
            }

        }

    fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState> {

        return when (stateEvent) {

            is GetBlogPostsEvent -> {
                AbsentLiveData.create()
            }

            is GetUserEvent -> {
                AbsentLiveData.create()
            }

            is None -> {
                AbsentLiveData.create()
            }

        }
    }

    fun setBlogListData(blogPosts: List<BlogPost>) {

        val update = getCurrentViewStateOrNew()
        update.blogPosts = blogPosts
        _viewState.value = update

    }

    fun setUser(user: User) {

        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update

    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }

    fun getCurrentViewStateOrNew(): MainViewState {
        return viewState.value?.let {
            it
        } ?: MainViewState()
    }

}