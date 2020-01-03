package com.baldystudios.androidmvibasic.ui.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baldystudios.androidmvibasic.R
import com.baldystudios.androidmvibasic.ui.DataStateListener
import com.baldystudios.androidmvibasic.ui.main.state.MainStateEvent.GetBlogPostsEvent
import com.baldystudios.androidmvibasic.ui.main.state.MainStateEvent.GetUserEvent

class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    lateinit var dataStateListener: DataStateListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        subscribeObservers()

    }

    fun subscribeObservers() {

        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->

            println("DEBUG: DataState: $dataState")

            // handle loading and message
            dataStateListener.onDataStateChange(dataState)

            dataState.data?.let { event ->

                event.getContentIfNotHandled()?.let { mainViewState ->

                    mainViewState.blogPosts?.let { blogPosts ->

                        viewModel.setBlogListData(blogPosts)

                    }

                    mainViewState.user?.let { user ->

                        viewModel.setUser(user)

                    }

                }


            }

        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->

            viewState.blogPosts?.let {

                println("DEBUG: Setting blog posts to RecyclerView: $it")

            }

            viewState.user?.let {

                println("DEBUG: Setting user data: $it")

            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.action_get_user -> triggerGetUserEvent()

            R.id.action_get_blogs -> triggerGetBlogsEvent()

        }

        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetBlogsEvent() {
        viewModel.setStateEvent(GetBlogPostsEvent())
    }

    private fun triggerGetUserEvent() {
        viewModel.setStateEvent(GetUserEvent("1"))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            dataStateListener = context as DataStateListener
        } catch (e: ClassCastException) {
            println("DEBUG: $context must implement DataStateListener")
        }

    }

}