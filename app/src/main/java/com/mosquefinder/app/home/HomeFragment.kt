package com.mosquefinder.app.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
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
    private lateinit var thur_time:TextView
    private lateinit var asr_time:TextView
    private lateinit var magrieb_time:TextView
    private lateinit var ishai_time:TextView
    private lateinit var currentTime: CurrentTime
    private lateinit var broadcastHandler: BroadcastHandler
    private lateinit var homeNavigator: HomeNavigator
    private lateinit var spinnerDropdown: Spinner

    val intentFilter = IntentFilter(Intent.ACTION_TIME_TICK)

    @SuppressLint("ResourceType")
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
//        spinnerDropdown = homeView.findViewById(R.id.location_spinner)
//        val locations = resources.getStringArray(R.array.locations)
//        val adapter = ArrayAdapter(homeView.context, R.array.locations, locations)
//        spinnerDropdown.adapter = adapter
        return homeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentTime.setInitalTime()
        this.navController = Navigation.findNavController(view)
        volleyRequest.volleyRequestDaily()
        broadcastHandler.setCurrentTimeCallbackListener(this)
        fajr_time = homeView.findViewById(R.id.fajr_time)
        thur_time = homeView.findViewById(R.id.thur_time)
        asr_time = homeView.findViewById(R.id.asr_time)
        magrieb_time = homeView.findViewById(R.id.magrieb_time)
        ishai_time = homeView.findViewById(R.id.ishai_time)
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
        fajr_time.text = fajr as String
    }

    override fun displayThurTime(thur: Any) {
        thur_time.text = thur as String
    }

    override fun displayAsrTime(asr: Any) {
        asr_time.text = asr as String
    }

    override fun displayMagirebTime(magrieb: Any) {
        magrieb_time.text = magrieb as String
    }

    override fun displayIshaiTime(ishai: Any) {
        ishai_time.text = ishai as String
    }

    override fun displayCurrentTime(time: Date) {
        currentTime.formatDate(time)
    }

}