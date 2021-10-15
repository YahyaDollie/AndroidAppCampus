package com.mosquefinder.app.home

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.mosquefinder.R
import java.text.SimpleDateFormat
import java.util.*

/*
This class contains the logic for determining how much time remains until the next salaah
 */

class CurrentTime(view:View) {

    private val remainingTimeLeft:TextView = view.findViewById(R.id.remainingTime)
    private lateinit var timeInString:String

    fun setInitalTime(){
        val intTime = Calendar.getInstance().time
        val formatter = SimpleDateFormat("HH:mm")
        val initalTime = formatter.format(intTime)
        remainingTimeLeft.text = initalTime
    }

    /*
    This function is only called once the minuite ticks and the reciver is triggered therefore an inital state should be set
     */
    fun formatDate(time: Date) {
        val formatter = SimpleDateFormat("HH:mm")
        timeInString = formatter.format(time)
        Log.d("formatDate: ", timeInString)
        remainingTimeLeft.text = timeInString
        calculateHour()
        calculateMin()
    }

    private fun calculateMin(){
        //#TODO(do min logic for current time vs next salaah time)
    }

    private fun calculateHour(){
        //#TODO(do hour logic for current time vs next salaah time)
    }

}