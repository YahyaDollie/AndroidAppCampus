package com.mosquefinder.app.home

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.mosquefinder.R
/*
This class sets the time for each Salaah, gets the times from the API
 */
class SalaahTimes(view: View) : FrameLayout(view.context), SalaahTimesHandler {

    private var fajr: TextView = view.findViewById(R.id.fajr_time)
    private var thur: TextView = view.findViewById(R.id.thur_time)
    private var asr: TextView = view.findViewById(R.id.asr_time)
    private var magrieb: TextView = view.findViewById(R.id.magrieb_time)
    private var ishai: TextView = view.findViewById(R.id.ishai_time)

    override fun setSalaahTimes() {

    }

    override fun setNextTime() {

    }

    fun getCurrentTime() {

    }

}