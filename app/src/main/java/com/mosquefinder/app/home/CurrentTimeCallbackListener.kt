package com.mosquefinder.app.home

import com.google.gson.JsonObject
import org.json.JSONObject
import java.util.*

interface CurrentTimeCallbackListener {
    fun displayCurrentTime(time: Date)
}