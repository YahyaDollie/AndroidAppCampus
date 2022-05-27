package com.mosquefinder.app.home

//TODO:LottieFile animation for day night cycle

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mosquefinder.R
import com.google.gson.GsonBuilder
import com.mosquefinder.app.Volley.CacheRequest
import com.mosquefinder.app.Volley.VolleyInterface
import com.mosquefinder.app.Volley.VolleyRequest
import com.mosquefinder.app.models.DailyModel
import java.util.*
import kotlin.math.log

/*
Main fragment for the home view
 */

class HomeFragment : Fragment() {
    val TAG = javaClass.simpleName
    private lateinit var homeView: View
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var remainingTimeHours: TextView
    private lateinit var remainingTimeMins: TextView
    private lateinit var clockTime: TextView
    private lateinit var fajrTime: TextView
    private lateinit var thurTime: TextView
    private lateinit var asrTime: TextView
    private lateinit var magriebTime: TextView
    private lateinit var ishaTime: TextView

    val url = "https://muslimsalat.com/cape-town.json?key=e7e6e40fc282866c47cda3e819fc9f04"

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        homeView = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        assignUIComponents()
        setSalaahTimes()
        setRemaningTime()
        clockTime.text = homeViewModel.getInitTime()
        setClockTime()
        return homeView
    }

    private fun assignUIComponents() {
        clockTime = homeView.findViewById(R.id.clockTime)
        fajrTime = homeView.findViewById(R.id.fajr_time)
        thurTime = homeView.findViewById(R.id.thur_time)
        asrTime = homeView.findViewById(R.id.asr_time)
        magriebTime = homeView.findViewById(R.id.magrieb_time)
        ishaTime = homeView.findViewById(R.id.ishai_time)

        remainingTimeHours = homeView.findViewById(R.id.remainingTime_hours)
        remainingTimeMins = homeView.findViewById(R.id.remainingTime_min)
    }

    private fun setRemaningTime() {
        val time = homeViewModel.getSalaahTimesAsDate(homeViewModel.times, homeViewModel.nextTime())
        Log.d(TAG, "${homeViewModel.nextTime()}, ${time[1]}, ${time[2]}")
        remainingTimeHours.text = "${time[1]}h"
        remainingTimeMins.text = "${time[2]}m"
    }

    private fun setClockTime(){
        homeViewModel.time.observe(viewLifecycleOwner, {
            clockTime.text = it
            Log.d(TAG, "setClockTime: $it")
        })
    }

    private fun setSalaahTimes() {
        homeViewModel.setSalaahTimes()
        fajrTime.text = homeViewModel.times[0]
        thurTime.text = homeViewModel.times[1]
        asrTime.text = homeViewModel.times[2]
        magriebTime.text = homeViewModel.times[3]
        ishaTime.text = homeViewModel.times[4]
    }

}