package com.mosquefinder.app.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import org.json.JSONArray
import org.json.JSONObject

/*
Main fragment for the home view
 */
class HomeFragment : Fragment() {
    private lateinit var homeView: View
    private lateinit var mapButton: FloatingActionButton
    private lateinit var mosqueButton: FloatingActionButton
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false)
        return homeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.navController = Navigation.findNavController(view)

        mapButton = view.findViewById(R.id.maps_button)
        mosqueButton = view.findViewById(R.id.mosque_button)
        volleyRequest()
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

    fun volleyRequest():String{
        val url = "https://muslimsalat.com/cape-town.json?key=e7e6e40fc282866c47cda3e819fc9f04"

        val requestQueue = Volley.newRequestQueue(context)
        val jsonRequestObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                var items = response.get("items") as JSONArray
                var items0 = items.get(0) as JSONObject
                var fajr = items0.get("fajr")
                Log.d("volleyRequest: ", fajr.toString())
            }, {
                Log.d("volleyRequest: ", "Error")
            })
        requestQueue.add(jsonRequestObjectRequest)
    }
}