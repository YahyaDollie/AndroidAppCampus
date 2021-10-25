package com.mosquefinder.app.network

import android.content.Context
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import com.mosquefinder.app.home.DailyTimesCallbackListener

class VolleyRequest(view: View) {
    val context: Context = view.context
    private val requestQueue:RequestQueue = Volley.newRequestQueue(context)
    private lateinit var dailyTimesCallbackListener: DailyTimesCallbackListener

    fun <T> addToRequestQueue(request: Request<T>) {
        requestQueue.add(request)
    }


    fun volleyRequestDaily(){
        val url = "https://muslimsalat.com/cape-town.json?key=e7e6e40fc282866c47cda3e819fc9f04"

        val requestQueue = Volley.newRequestQueue(context)
        val jsonRequestObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                val gson = GsonBuilder().create()
                val obj = gson.fromJson(response.toString(), TimeModel::class.java)

                val times = obj.items[0]

                triggerCallback(times.fajr, times.dhuhr, times.asr, times.maghrib, times.isha)
            }, {

            })
        requestQueue.add(jsonRequestObjectRequest)
    }

    fun setDailyTimeCallbackListener(dailyTimesCallbackListener: DailyTimesCallbackListener){
        this.dailyTimesCallbackListener = dailyTimesCallbackListener
    }

    private fun triggerCallback(
        fajr: String,
        dhuhr: String,
        asr: String,
        maghrib: String,
        isha: String
    ) {
        dailyTimesCallbackListener.displayFajrTime(fajr)
        dailyTimesCallbackListener.displayThurTime(dhuhr)
        dailyTimesCallbackListener.displayAsrTime(asr)
        dailyTimesCallbackListener.displayMagirebTime(maghrib)
        dailyTimesCallbackListener.displayIshaiTime(isha)
    }
}
