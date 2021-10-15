package com.mosquefinder.app.home

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mosquefinder.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mosquefinder.app.network.VolleyRequest
import java.util.*

/*
Main fragment for the home view
 */

class HomeFragment : Fragment(), DailyTimesCallbackListener, CurrentTimeCallbackListener {
    private lateinit var homeView: View
    private lateinit var mapButton: FloatingActionButton
    private lateinit var mosqueButton: FloatingActionButton
    private lateinit var calendarButton: FloatingActionButton
    private lateinit var navController: NavController
    private lateinit var volleyRequest: VolleyRequest
    private lateinit var fajrTime:String
    private lateinit var fajr_time:TextView
    private lateinit var currentTime: CurrentTime
    private lateinit var broadcastHandler: BroadcastHandler
    private lateinit var homeNavigator: HomeNavigator

    val intentFilter = IntentFilter(Intent.ACTION_TIME_TICK)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false)
        volleyRequest = VolleyRequest(homeView)
        volleyRequest.setDailyTimeCallbackListener(this)
        currentTime = CurrentTime(homeView)
        broadcastHandler = BroadcastHandler()
        return homeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentTime.setInitalTime()
        this.navController = Navigation.findNavController(view)
        volleyRequest.volleyRequestDaily()
        broadcastHandler.setCurrentTimeCallbackListener(this)
        fajr_time = homeView.findViewById(R.id.fajr_time)
        registerReciver()
        handleNavigation()
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

    private fun registerReciver(){
        requireActivity().registerReceiver(broadcastHandler, intentFilter)
    }

    private fun unregisterReciver(){
        requireActivity().unregisterReceiver(broadcastHandler)
    }

    private fun handleNavigation(){
        mosqueButton = homeView.findViewById(R.id.mosque_button)
        mosqueButton.setOnClickListener {
            this.navController.navigate(R.id.action_homeFragment_to_mosqueFind)
        }
        mapButton = homeView.findViewById(R.id.maps_button)
        mapButton.setOnClickListener {
            this.navController.navigate(R.id.action_home_fragment_to_mapsFragment)
        }
        calendarButton = homeView.findViewById(R.id.calendar_button)
        calendarButton.setOnClickListener {
            this.navController.navigate(R.id.action_home_fragment_to_calendar)
        }
    }

    override fun displayFajrTime(fajr: Any) {
        fajrTime = fajr.toString()
        fajr_time.text = fajrTime
    }

    override fun displayCurrentTime(time: Date) {
        currentTime.formatDate(time)
    }

}