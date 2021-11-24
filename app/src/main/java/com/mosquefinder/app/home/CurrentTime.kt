package com.mosquefinder.app.home

import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.volley.toolbox.Volley
import com.example.mosquefinder.R
import com.google.gson.GsonBuilder
import com.mosquefinder.app.models.DailyModel
import org.json.JSONObject
import java.lang.IndexOutOfBoundsException
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime
import java.util.*

/*
This class contains the logic for determining how much time remains until the next salaah
 */

class CurrentTime(view: View) {

    private val clockTime: TextView = view.findViewById(R.id.clockTime)
    private val nextSalaah: TextView = view.findViewById(R.id.nextSalaah)
    private var hourRemaining: TextView = view.findViewById(R.id.remainingTime_hours)
    private val minRemaining: TextView = view.findViewById(R.id.remainingTime_min)

    private lateinit var timeInString: String
    private lateinit var newTimes: Array<String?>
    private val initTimeAsDate: Date = Calendar.getInstance().time
    private val twentyFourHourFormatter = SimpleDateFormat("HH:mm")
    private val twelveHourFormatter = SimpleDateFormat("hh:mm aa")
    private val initTime: String = twentyFourHourFormatter.format(initTimeAsDate)

    fun setInitTime() {
        clockTime.text = initTime
    }

    fun setInitRemainingTime(response: JSONObject) {
        val gson = GsonBuilder().create()
        val obj = gson.fromJson(response.toString(), DailyModel::class.java)

        val times = arrayOf(
            obj.items[0].fajr,
            "1:00 pm",
            obj.items[0].asr,
            obj.items[0].maghrib,
            obj.items[0].isha,
        )

        val fajr: String? = twentyFourHourFormatter.format(twelveHourFormatter.parse(times[0]))
        val thur: String? = twentyFourHourFormatter.format(twelveHourFormatter.parse(times[1]))
        val asr: String? = twentyFourHourFormatter.format(twelveHourFormatter.parse(times[2]))
        val magrieb: String? = twentyFourHourFormatter.format(twelveHourFormatter.parse(times[3]))
        val isha: String? = twentyFourHourFormatter.format(twelveHourFormatter.parse(times[4]))

        newTimes = arrayOf(
            fajr,
            thur,
            asr,
            magrieb,
            isha
        )

        salaahIntervals(initTime)
    }

    private fun salaahIntervals(time: String) {
        when (true) {
            isBetween(4, 0) -> {
                nextSalaah.text = "Fajr"
                val remainingTime = initDurationTime(0, time)
                try {
                    minRemaining.text = "Minutes left " + remainingTime[1]
                    hourRemaining.text = "Hour left " + remainingTime[0]
                } catch (e: IndexOutOfBoundsException) {
                    minRemaining.text = "Minutes left " + remainingTime[0]
                    hourRemaining.text = ""
                }
            }
            isBetween(0, 1) -> {
                nextSalaah.text = "Thur"
                val remainingTime = initDurationTime(1, time)
                try {
                    minRemaining.text = "Minutes left " + remainingTime[1]
                    hourRemaining.text = "Hour left " + remainingTime[0]
                } catch (e: IndexOutOfBoundsException) {
                    minRemaining.text = "Minutes left " + remainingTime[0]
                    hourRemaining.text = ""
                }
            }
            isBetween(1, 2) -> {
                nextSalaah.text = "Asr"
                val remainingTime = initDurationTime(2, time)
                try {
                    minRemaining.text = "Minutes left " + remainingTime[1]
                    hourRemaining.text = "Hour left " + remainingTime[0]
                } catch (e: IndexOutOfBoundsException) {
                    minRemaining.text = "Minutes left " + remainingTime[0]
                    hourRemaining.text = ""
                }
            }
            isBetween(2, 3) -> {
                nextSalaah.text = "Magrieb"
                val remainingTime = initDurationTime(3, time)
                try {
                    minRemaining.text = "Minutes left " + remainingTime[1]
                    hourRemaining.text = "Hour left " + remainingTime[0]
                } catch (e: IndexOutOfBoundsException) {
                    minRemaining.text = "Min left " + remainingTime[0]
                    hourRemaining.text = ""
                }
            }
            isBetween(3, 4) -> {
                nextSalaah.text = "Isha"
                val remainingTime = initDurationTime(4, time)
                try {
                    minRemaining.text = "Minutes left " + remainingTime[1]
                    hourRemaining.text = "Hour left " + remainingTime[0]
                } catch (e: IndexOutOfBoundsException) {
                    minRemaining.text = "Minutes left " + remainingTime[0]
                    hourRemaining.text = ""
                }
            }
        }
    }

    private fun initDurationTime(index: Int, time: String): List<String> {
        var nextSalaah = twentyFourHourFormatter.parse(newTimes[index])
        var currentTime = twentyFourHourFormatter.parse(time)
        return Duration.between(currentTime.toInstant(), nextSalaah.toInstant())
            .toString()
            .replace("PT", "")
            .replace("M", "")
            .split("H")
    }

    private fun isBetween(startIndex: Int, endIndex: Int): Boolean {
        val previousSalaahTime: LocalTime = LocalTime.parse(newTimes[startIndex])
        val nextSalaahTime: LocalTime = LocalTime.parse(newTimes[endIndex])
        val currentTime: LocalTime = LocalTime.parse(initTime)
        return if (endIndex == 0) {
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
        salaahIntervals(timeInString)
    }

}