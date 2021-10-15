package com.mosquefinder.app.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mosquefinder.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mosquefinder.app.mosquefind.MosqueFind
import com.mosquefinder.app.network.VolleyRequest
import org.json.JSONArray
import org.json.JSONObject

/*
Main fragment for the home view
 */
class HomeFragment : Fragment(), DailyTimesCallbackListener {
    private lateinit var homeView: View
    private lateinit var mapButton: FloatingActionButton
    private lateinit var mosqueButton: FloatingActionButton
    private lateinit var navController: NavController
    private lateinit var volleyRequest: VolleyRequest
    private lateinit var fajrTime:String
    private lateinit var fajr_time:TextView
    private lateinit var nextSalaahTime:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false)
        volleyRequest = VolleyRequest(homeView)
        volleyRequest.setDailyTimeCallbackListener(this)
        return homeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.navController = Navigation.findNavController(view)
        volleyRequest.volleyRequestDaily()
        mapButton = view.findViewById(R.id.maps_button)
        mosqueButton = view.findViewById(R.id.mosque_button)
        fajr_time = homeView.findViewById(R.id.fajr_time)
        nextSalaahTime = homeView.findViewById(R.id.remainingTime)
        mosqueButton.setOnClickListener {
            this.navController.navigate(R.id.action_homeFragment_to_mosqueFind)
        }
        mapButton.setOnClickListener {
            this.navController.navigate(R.id.action_home_fragment_to_mapsFragment)
        }
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

    override fun displayFajrTime(fajr: Any) {
        fajrTime = fajr.toString()
        fajr_time.text = fajrTime
    }


}