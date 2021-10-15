package com.mosquefinder.app.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import java.time.LocalTime
import java.util.*
/*
This class gets and updates the current time
 */
class CurrentTime(): BroadcastReceiver() {

    private lateinit var currentTimeCallbackListener: CurrentTimeCallbackListener
    private lateinit var date: Date
    private lateinit var updatedCurrentTime: String

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action?.compareTo(Intent.ACTION_TIME_TICK) == 0) {
            date = Calendar.getInstance().time
            updatedCurrentTime = date.toString()
            currentTimeCallbackListener.displayCurrentTime(updatedCurrentTime)
            Log.d("onReceive: ", date.toString())
            }
    }

    fun setCurrentTimeCallbackListener(currentTimeCallbackListener: CurrentTimeCallbackListener){
        this.currentTimeCallbackListener = currentTimeCallbackListener
    }
}