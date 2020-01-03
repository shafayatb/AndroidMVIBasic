package com.baldystudios.androidmvibasic.ui

import com.baldystudios.androidmvibasic.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)

}