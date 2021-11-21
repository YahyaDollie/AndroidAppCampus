package com.mosquefinder.app.home

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.mosquefinder.R

class SalaahTimes(view: View) : FrameLayout(view.context) {

    private var fajr: TextView = view.findViewById(R.id.fajr_time)
    private var zenith: TextView = view.findViewById(R.id.zenith_time)
    private var thur: TextView = view.findViewById(R.id.thur_time)
    private var asr: TextView = view.findViewById(R.id.asr_time)
    private var magrieb: TextView = view.findViewById(R.id.magrieb_time)
    private var ishai: TextView = view.findViewById(R.id.ishai_time)

    fun setSalaahTimes(fajrTime:String, thurTime:String, asrTime:String, magriebTime:String, ishaiTime:String) {
            fajr.text = fajrTime
            zenith.text = thurTime
            thur.text = "13:00 pm"
            asr.text = asrTime
            magrieb.text = magriebTime
            ishai.text = ishaiTime
    }

}