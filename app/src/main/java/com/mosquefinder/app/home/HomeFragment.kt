package com.mosquefinder.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mosquefinder.R
import com.mosquefinder.app.api.Item

/*
Main fragment for the home view
 */
class HomeFragment : Fragment() {
    private lateinit var homeView: View
    private lateinit var salaahTimes: SalaahTimes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false)
        salaahTimes = SalaahTimes(homeView)
        return homeView
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}