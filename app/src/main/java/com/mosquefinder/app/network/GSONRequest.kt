package com.mosquefinder.app.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class GSONRequest(context: Context) {
    val requestQueue = Volley.newRequestQueue(context)

    fun jsonParse() {
        val url = "https://muslimsalat.com/cape-town.json?key=e7e6e40fc282866c47cda3e819fc9f04"
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->


            }, {

            })
    }
}