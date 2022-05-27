package com.mosquefinder.app.home

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.text.SimpleDateFormat
import java.util.*

/*
This class gets and updates the current time
 */
class BroadcastHandler(application: Application) : BroadcastReceiver() {

//    private val currentTimeCallbackListener: CurrentTimeCallbackListener = HomeViewModel(application)
    private lateinit var updatedCurrentTime: Date

    val _updatedTime = MutableLiveData<String>()
    val updatedTime : LiveData<String>
        get() = _updatedTime

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action?.compareTo(Intent.ACTION_TIME_TICK) == 0) {
            updatedCurrentTime = Calendar.getInstance().time
//            currentTimeCallbackListener.displayCurrentTime(updatedCurrentTime)
            Toast.makeText(context, "Clock tick", Toast.LENGTH_LONG).show()
            _updatedTime.value = SimpleDateFormat("HH:mm", Locale("UK")).format(updatedCurrentTime)
        }
    }
}