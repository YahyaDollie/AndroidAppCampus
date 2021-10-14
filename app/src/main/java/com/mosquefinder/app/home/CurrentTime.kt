package com.mosquefinder.app.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import java.util.*
/*
This class gets and updates the current time
 */
class CurrentTime() {

    val timeBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action?.compareTo(Intent.ACTION_TIME_TICK) == 0) {

            }
        }
    }
}