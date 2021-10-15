package com.mosquefinder.app.network

import android.content.Context
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.mosquefinder.app.home.DailyTimesCallbackListener
import org.json.JSONArray
import org.json.JSONObject

class VolleyRequest(view: View) {

    val context: Context = view.context
    private lateinit var dailyTimesCallbackListener: DailyTimesCallbackListener

    fun volleyRequestDaily(){
        val url = "https://muslimsalat.com/cape-town.json?key=e7e6e40fc282866c47cda3e819fc9f04"

        val requestQueue = Volley.newRequestQueue(context)
        val jsonRequestObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                var items = response.get("items") as JSONArray
                var items0 = items.get(0) as JSONObject
                var fajr = items0.get("fajr")
                var thur = items0.get("dhuhr")
                var asr = items0.get("asr")
                var magrieb = items0.get("magrieb")
                var ishai = items0.get("isha")
                dailyTimesCallbackListener.displayFajrTime(fajr)
                dailyTimesCallbackListener.displayThurTime(thur)
                dailyTimesCallbackListener.displayAsrTime(asr)
                dailyTimesCallbackListener.displayMagirebTime(magrieb)
                dailyTimesCallbackListener.displayIshaiTime(ishai)
                Log.d("volleyRequest: ", fajr.toString())
            }, {
                Log.d("volleyRequest: ", it.toString())
            })
        requestQueue.add(jsonRequestObjectRequest)
    }

    fun setDailyTimeCallbackListener(dailyTimesCallbackListener: DailyTimesCallbackListener){
        this.dailyTimesCallbackListener = dailyTimesCallbackListener
    }
}