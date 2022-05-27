package com.mosquefinder.app.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

class CurrentTime() {
    private val TAG = javaClass.simpleName
    private var _time = MutableLiveData<String>()

//    private fun formatDate(time: Date) {
//        val timeInString = SimpleDateFormat("HH:mm", Locale("UK")).format(time)
//        Log.d(TAG, timeInString)
//        _time.value = timeInString
//    }
//
//    override fun displayCurrentTime(time: Date) {
//        formatDate(time)
//    }
}