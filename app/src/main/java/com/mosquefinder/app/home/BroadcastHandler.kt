package com.mosquefinder.app.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.util.*

/*
This class gets and updates the current time
 */
class BroadcastHandler() : BroadcastReceiver() {

    private lateinit var currentTimeCallbackListener: CurrentTimeCallbackListener
    private lateinit var updatedCurrentTime: Date

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action?.compareTo(Intent.ACTION_TIME_TICK) == 0) {
            updatedCurrentTime = Calendar.getInstance().time
            currentTimeCallbackListener.displayCurrentTime(updatedCurrentTime)
        }
    }

    fun setCurrentTimeCallbackListener(currentTimeCallbackListener: CurrentTimeCallbackListener) {
        this.currentTimeCallbackListener = currentTimeCallbackListener
    }
}