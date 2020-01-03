package com.baldystudios.androidmvibasic.ui.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.baldystudios.androidmvibasic.R
import com.baldystudios.androidmvibasic.ui.DataStateListener
import com.baldystudios.androidmvibasic.util.DataState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    DataStateListener {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        showMainFragment()

    }

    override fun onDataStateChange(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    fun showMainFragment() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment(), "MainFragment")
            .commit()

    }

    private fun handleDataStateChange(dataState: DataState<*>?) {
        dataState?.let { it ->

            //handle loading
            showProgressBar(it.loading)

            //handle message
            dataState.message?.let { event ->

                event.getContentIfNotHandled()?.let { message ->

                    showToast(message)

                }
            }
        }
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProgressBar(isVisible: Boolean) {
        progress_bar.visibility = if (isVisible) VISIBLE else GONE
    }

}
