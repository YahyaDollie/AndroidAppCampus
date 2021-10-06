package com.mosquefinder.app

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime
import java.util.*

class TimeDisplay : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val dataBaseHelper: DataBaseHelper = DataBaseHelper(context)

    private var currentTimeInString:String
    private lateinit var remainingTime:String
    private var currentTime:LocalTime

    init {
        currentTimeInString = getCurrentDateTime().toString("HH:mm")
        currentTime = LocalTime.parse(currentTimeInString)
    }


    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
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

    private fun displayRemainingTime(endIndex: Int): String? {
        var nextSalaahTime: LocalTime = LocalTime.parse(dataBaseHelper.salaahTimes[endIndex])
        var currentTime: LocalTime = LocalTime.parse(currentTimeInString)
        var remainingTime:String

        if (Duration.between(currentTime, nextSalaahTime).isNegative){
            return null
        } else {
            remainingTime = if (calculateMinRemaning() != null) {
                calculateHoursRemaining() + "H and " + calculateMinRemaning() + "m is remaining till:"
            } else {
                calculateHoursRemaining() + "m remaining till"
            }

            return remainingTime
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

    private fun calculateMinRemaning(): String? {
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
