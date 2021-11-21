package com.mosquefinder.app.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mosquefinder.R
import com.google.gson.GsonBuilder
import com.mosquefinder.app.models.DailyModel
import org.json.JSONObject
import java.util.*

/*
Main fragment for the home view
 */

class HomeFragment : Fragment(), CurrentTimeCallbackListener {
    private lateinit var homeView: View
    private lateinit var navController: NavController
    private lateinit var currentTime: CurrentTime
    private lateinit var broadcastHandler: BroadcastHandler
    private lateinit var salaahTimes: SalaahTimes
    private lateinit var spinnerDropdown: Spinner

    val intentFilter = IntentFilter(Intent.ACTION_TIME_TICK)

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false)
        currentTime = CurrentTime(homeView)
        salaahTimes = SalaahTimes(homeView)
        broadcastHandler = BroadcastHandler()
        registerReciver()
        extractDate()
        return homeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentTime.setInitTime()
        this.navController = Navigation.findNavController(view)
        broadcastHandler.setCurrentTimeCallbackListener(this)
        setupSpinner()
    }

    private fun registerReciver() {
        requireActivity().registerReceiver(broadcastHandler, intentFilter)
    }

    private fun unregisterReciver() {
        requireActivity().unregisterReceiver(broadcastHandler)
    }

    private fun setupSpinner() {
        spinnerDropdown = homeView.findViewById(R.id.location_spinner)
        var options = arrayOf("Cape Town")
        spinnerDropdown.adapter =
            ArrayAdapter(homeView.context, android.R.layout.simple_spinner_dropdown_item, options)
        spinnerDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                //TODO: Implement selectable cities
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //Ignore
            }

        }
    }

    private fun extractDate() {
        val url = "https://muslimsalat.com/cape-town.json?key=e7e6e40fc282866c47cda3e819fc9f04"
        val requestQueue = Volley.newRequestQueue(context)
        val jsonRequestObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                gsonParserDaily(response)
                currentTime.setInitRemainingTime(response)
            }, {

            }
        )
        requestQueue.add(jsonRequestObjectRequest)
    }

    private fun gsonParserDaily(response: JSONObject) {
        val gson = GsonBuilder().create()
        val obj = gson.fromJson(response.toString(), DailyModel::class.java)
        val times = obj.items[0]

        salaahTimes.setSalaahTimes(times.fajr, times.dhuhr, times.asr, times.maghrib, times.isha)
    }

    override fun displayCurrentTime(time: Date) {
        currentTime.formatDate(time)
    }

}