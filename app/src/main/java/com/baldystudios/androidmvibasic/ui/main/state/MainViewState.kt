package com.baldystudios.androidmvibasic.ui.main.state

import com.baldystudios.androidmvibasic.model.BlogPost
import com.baldystudios.androidmvibasic.model.User

data class MainViewState (

    var blogPosts: List<BlogPost>? = null,
    var user: User? = null

)