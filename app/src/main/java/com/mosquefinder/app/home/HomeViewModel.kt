package com.mosquefinder.app.home

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*
import java.util.function.BiFunction


@SuppressLint("StaticFieldLeak")
class HomeViewModel(application: Application) : AndroidViewModel(application){
    private lateinit var applicationContext: Context
    val TAG = javaClass.simpleName
    val times = setSalaahTimes()
    private lateinit var remainingTimes: ArrayList<Long>
    val twentyFourHourPattern = "HH:mm"
    val twelveHourPattern = "hh:mm"

    /***
     * Function to get the time on start up
     * @return Time (24-hours) as String
     */
    fun getInitTime(): String {
        val initTimeAsDate: Date = Calendar.getInstance().time
        return SimpleDateFormat(twentyFourHourPattern, Locale("UK")).format(initTimeAsDate)
    }

    //TODO:This method will pull the data from the api and set those times in this Array
    fun setSalaahTimes(): ArrayList<String> {
        return arrayListOf(
            "06:00",
            "13:00",
            "16:00",
            "19:00",
            "20:00"
        )
    }

    /**
     * Method that determines which Salaah time is next relative to the current time
     * @return returns an index that can be used to pull the next Salaah
     */
    fun nextTime(): Int {
        val format = SimpleDateFormat(twentyFourHourPattern, Locale.UK)
        var index = 0

        val currentTime = format.parse(getInitTime())
        val salaahTimes = setSalaahTimes()
        val fajrTime = format.parse(salaahTimes[0])
        val thurTime = format.parse(salaahTimes[1])
        val asrTime = format.parse(salaahTimes[2])
        val magriebTime = format.parse(salaahTimes[3])
        val ishaTime = format.parse(salaahTimes[4])

        if (currentTime != null) {
            if (currentTime.after(ishaTime) && currentTime.before(fajrTime)) {
                index = 0
            } else if (currentTime.after(fajrTime) && currentTime.before(thurTime)) {
                index = 1
            } else if (currentTime.after(thurTime) && currentTime.before(asrTime)) {
                index = 2
            } else if (currentTime.after(asrTime) && currentTime.before(magriebTime)) {
                index = 3
            } else if (currentTime.after(magriebTime) && currentTime.before(ishaTime)) {
                index = 4
            }
        }
        return index
    }

    /**
     * Method that converts the Salaah times to date for the difference to be calculated
     * @param times {List of Salaah times}
     * @param index {Which Salaah time is next to be calculated}
     * @return List of the remaining time broken down into hours,mins,seconds
     */
    fun getSalaahTimesAsDate(times: ArrayList<String>, index: Int): ArrayList<Long> {
        val format = SimpleDateFormat(twentyFourHourPattern, Locale.UK)
        val currentTime = format.parse(getInitTime()) //TODO:This time needs to be updated at every clock tick
        val nextSalaah = format.parse(times[index])
        if (currentTime != null && nextSalaah != null)
            remainingTimes = calculateDiffrence(currentTime, nextSalaah)
        return remainingTimes
    }

    /**
     * Method that calculates the difference between two times
     * @param startDate
     * @param endDate
     * @return List of elapsed times (Days, hours, minutes, seconds)
     */
    private fun calculateDiffrence(startDate: Date, endDate: Date): ArrayList<Long> {
        var different = endDate.time - startDate.time

        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        val elapsedDays = different / daysInMilli
        different %= daysInMilli

        var elapsedHours = different / hoursInMilli
        different %= hoursInMilli

        var elapsedMinutes = different / minutesInMilli
        different %= minutesInMilli

        val elapsedSeconds = different / secondsInMilli

        /**
         * If statements are incase of negative time difference due ie.
         * Time difference from 20:59 to 06:00 will be -14h -59m
         * Yet what we want is 10h 1m which is the actual time difference
         */
        if (elapsedHours < 0) {
            elapsedHours += 24
        }

        if (elapsedMinutes < 0) {
            elapsedMinutes += 60
        }

        return arrayListOf(
            elapsedDays,
            elapsedHours,
            elapsedMinutes,
            elapsedSeconds
        )
    }
}