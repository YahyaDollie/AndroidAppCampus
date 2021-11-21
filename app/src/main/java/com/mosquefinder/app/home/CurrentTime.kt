package com.mosquefinder.app.home

import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.mosquefinder.R
import com.google.gson.GsonBuilder
import com.mosquefinder.app.models.DailyModel
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

/*
This class contains the logic for determining how much time remains until the next salaah
 */

class CurrentTime(view:View) {

    private val clockTime:TextView = view.findViewById(R.id.clockTime)
    private val nextSalaah:TextView = view.findViewById(R.id.nextSalaah)
    private var hourRemaining:TextView = view.findViewById(R.id.remainingTime_hours)
    private val minRemaining:TextView = view.findViewById(R.id.remainingTime_min)

    private lateinit var timeInString:String
    private lateinit var newTimes: Array<String?>
    private val initTimeAsDate : Date = Calendar.getInstance().time
    private val twentyFourHourFormatter = SimpleDateFormat("HH:mm")
    private val twelveHourFormatter = SimpleDateFormat("hh:mm aa")
    private val initTime: String = twentyFourHourFormatter.format(initTimeAsDate)

    fun setInitTime(){
        clockTime.text = initTime
    }

    fun setInitRemainingTime(response: JSONObject){
        val gson = GsonBuilder().create()
        val obj = gson.fromJson(response.toString(), DailyModel::class.java)

        val times = arrayOf(
            obj.items[0].fajr,
            "1:00 pm",
            obj.items[0].asr,
            obj.items[0].maghrib,
            obj.items[0].isha,
        )

        val fajr: String? = twentyFourHourFormatter?.format(twelveHourFormatter.parse(times[0]))
        val thur: String? = twentyFourHourFormatter?.format(twelveHourFormatter.parse(times[1]))
        val asr: String? = twentyFourHourFormatter?.format(twelveHourFormatter.parse(times[2]))
        val magrieb: String? = twentyFourHourFormatter?.format(twelveHourFormatter.parse(times[3]))
        val isha: String? = twentyFourHourFormatter?.format(twelveHourFormatter.parse(times[4]))

        newTimes = arrayOf(
            fajr,
            thur,
            asr,
            magrieb,
            isha
        )

        when (true) {
            isBetween(4,0) -> nextSalaah.text = "Fajr"
            isBetween(0,1) -> nextSalaah.text = "Thur"
            isBetween(1,2) -> nextSalaah.text = "Asr"
            isBetween(2,3) -> nextSalaah.text = "Magrieb"
            isBetween(3,4) -> nextSalaah.text = "Isha"
        }
    }

    private fun isBetween(startIndex:Int, endIndex:Int): Boolean {
        val previousSalaahTime: LocalTime = LocalTime.parse(newTimes[startIndex])
        val nextSalaahTime: LocalTime = LocalTime.parse(newTimes[endIndex])
        val currentTime: LocalTime = LocalTime.parse(initTime)

        return if (endIndex == 0){
            currentTime.isAfter(previousSalaahTime)
        } else {
            currentTime.isBefore(nextSalaahTime) && currentTime.isAfter(previousSalaahTime)
        }
    }

    /*
    This function is only called once the minute ticks and the receiver is triggered therefore an init state should be set
     */
    fun formatDate(time: Date) {
        val formatter = SimpleDateFormat("HH:mm")
        timeInString = formatter.format(time)
        Log.d("formatDate: ", timeInString)
        clockTime.text = timeInString
        calculateHour()
        calculateMin()
    }



    private fun calculateMin(){
        minRemaining.text = "0 Minutes"
    }

    private fun calculateHour(){
        hourRemaining.text = "4 Hours"
    }

}