package com.mosquefinder.app

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mosquefinder.R
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var dataBaseHelper:DataBaseHelper
    private lateinit var fajr_time:TextView
    private lateinit var thur_time:TextView
    private lateinit var asr_time:TextView
    private lateinit var magrieb_time:TextView
    private lateinit var ishai_time:TextView
    private lateinit var timehour:TextView
    private lateinit var timemin:TextView
    private lateinit var nextSalaah:TextView
    private var start: LocalTime = LocalTime.parse("08:00")
    private var stop: LocalTime = LocalTime.parse("08:00")
    private lateinit var duration:Duration
    private lateinit var remainingTime:String
    private val date = getCurrentDateTime()
    private val dateInString = date.toString("HH:mm")
    private var currentTime: LocalTime = LocalTime.parse(dateInString)

    private val FAJR = "Fajr"
    private val THUR = "Thur"
    private val ASR = "Asr"
    private val MAGRIEB = "Magrieb"
    private val ISHAI = "Ishai"


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBaseHelper = DataBaseHelper(this)
        dataBaseHelper.addTime()

//        val duration = Duration.between(start, stop).toString()
//
//        var timeHours:String =
//            if (duration[2].isDigit() && duration[3].isDigit()) {
//            "Hours is :" + duration[2] + duration[3]
//            } else {
//            "Hours is :" + duration[2]
//            }
//        var timeMinuites: String? = null
//        try {
//            if (duration[5].isDigit() && duration[6].isDigit()) {
//                timeMinuites = "Minuites is :" + duration[5] + duration[6]
//            }
//        } catch (e : StringIndexOutOfBoundsException){
//            timeMinuites = null
//        }


        fajr_time = findViewById(R.id.fajr_time)
        thur_time = findViewById(R.id.thur_time)
        asr_time = findViewById(R.id.asr_time)
        magrieb_time = findViewById(R.id.magrieb_time)
        ishai_time = findViewById(R.id.ishai_time)
        timemin = findViewById(R.id.remainingTime)
        nextSalaah = findViewById(R.id.nextSalaah)

        fajr_time.text = dataBaseHelper.salaahTimes[0]
        thur_time.text = dataBaseHelper.salaahTimes[1]
        asr_time.text = dataBaseHelper.salaahTimes[2]
        magrieb_time.text = dataBaseHelper.salaahTimes[3]
        ishai_time.text = dataBaseHelper.salaahTimes[4]

//        timehour.text = timeHours
//        timemin.text = timeMinuites
//        if (timeMinuites == null){
//            timehour.text = null
//            timemin.text = timeHours
//        }
//



        Log.d("TAG", isBetween(4,0).toString())

        when (true) {
            isBetween(4,0) -> nextSalaah.text = "Fajr"
            isBetween(0,1) -> nextSalaah.text = "Thur"
            isBetween(1,2) -> nextSalaah.text = "Asr"
            isBetween(2,3) -> nextSalaah.text = "Magrieb"
            isBetween(3,4) -> nextSalaah.text = "Ishai"
        }

        when (nextSalaah.text){
            "Fajr" -> timemin.text = displayRemainingTime(0)
            "Thur" -> timemin.text = displayRemainingTime(1)
            "Asr" -> timemin.text = displayRemainingTime(2)
            "Magrieb" -> timemin.text = displayRemainingTime(3)
            "Ishai" -> timemin.text = displayRemainingTime(4)
        }
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun displayRemainingTime(endIndex: Int): String? {
        var nextSalaahTime:LocalTime = LocalTime.parse(dataBaseHelper.salaahTimes[endIndex])

        if (Duration.between(currentTime, nextSalaahTime).isNegative){
            return null
        } else {
            remainingTime = Duration.between(currentTime, nextSalaahTime).toString()
            remainingTime = if (calculateMinRemaning() != null) {
                calculateHoursRemaining() + "H and " + calculateMinRemaning() + "m is remaining till:"
            } else {
                calculateHoursRemaining() + "m remaining till"
            }

            return remainingTime
        }
    }

    private fun isBetween(startIndex:Int, endIndex:Int): Boolean{
        val previousSalaahTime: LocalTime = LocalTime.parse(dataBaseHelper.salaahTimes[startIndex])
        val nextSalaahTime: LocalTime = LocalTime.parse(dataBaseHelper.salaahTimes[endIndex])
        Log.d("isBetween: ", currentTime.toString() + " " + previousSalaahTime.toString() + " " + nextSalaahTime.toString() + " : " + (currentTime.isBefore(nextSalaahTime) && currentTime.isAfter(previousSalaahTime)))

        if (endIndex == 0){
            return currentTime.isAfter(previousSalaahTime)
        } else {
            return currentTime.isBefore(nextSalaahTime) && currentTime.isAfter(previousSalaahTime)
        }



    }

    private fun calculateHoursRemaining(): String{
        var firstHourDigit:Char?
        var secondHourDigit:Char?
        val hourRemaining:String?

        if (remainingTime[2].isDigit() && remainingTime[3].isDigit()) {
            firstHourDigit = remainingTime[2]
            secondHourDigit = remainingTime[3]
            hourRemaining = firstHourDigit.toString() + secondHourDigit.toString()
            return hourRemaining
        } else {
            firstHourDigit = remainingTime[2]
            hourRemaining = firstHourDigit.toString()
            return hourRemaining
        }
    }

    fun calculateMinRemaning(): String? {
        var firstMinDigit: Char?
        var secondMinDigit: Char?
        var minRemaining: String?
        try {
            if (remainingTime[4].isDigit() && !remainingTime[5].isDigit()) {
                firstMinDigit = remainingTime[4]
                minRemaining = firstMinDigit.toString()
                return minRemaining
            } else if (remainingTime[4].isDigit() && remainingTime[5].isDigit()) {
                firstMinDigit = remainingTime[4]
                secondMinDigit = remainingTime[5]
                minRemaining = firstMinDigit.toString() + secondMinDigit.toString()
                return minRemaining
            } else if (!remainingTime[4].isDigit() && remainingTime[5].isDigit() && !remainingTime[6].isDigit()) {
                firstMinDigit = remainingTime[5]
                minRemaining = firstMinDigit.toString()
                return minRemaining
            } else if (remainingTime[5].isDigit() && remainingTime[6].isDigit()) {
                firstMinDigit = remainingTime[5]
                secondMinDigit = remainingTime[6]
                minRemaining = firstMinDigit.toString() + secondMinDigit.toString()
                return minRemaining
            } else {
                minRemaining = null
                return minRemaining
            }
        } catch (e: StringIndexOutOfBoundsException) {
            Log.d(
                e.toString(),
                "Indication of no hour value present therefore we can use the hour calculation function to display minuites"
            )
            minRemaining = null
            return minRemaining
        }
    }
}