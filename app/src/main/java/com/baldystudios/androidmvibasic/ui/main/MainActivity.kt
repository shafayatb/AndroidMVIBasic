package com.baldystudios.androidmvibasic.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.baldystudios.androidmvibasic.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMainFragment()

    }

    fun showMainFragment() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment(), "MainFragment")
            .commit()

    }

}
