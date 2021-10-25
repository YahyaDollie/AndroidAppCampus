package com.mosquefinder.app.home

import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.mosquefinder.R
import com.mosquefinder.app.network.VolleyRequest

/*
This class sets the time for each Salaah, gets the times from the API and calculates time till next salaah
 */

class SalaahTimes(view: View) : FrameLayout(view.context), DailyTimesCallbackListener {

    private var fajr: TextView = view.findViewById(R.id.fajr_time)
    private var thur: TextView = view.findViewById(R.id.thur_time)
    private var asr: TextView = view.findViewById(R.id.asr_time)
    private var magrieb: TextView = view.findViewById(R.id.magrieb_time)
    private var ishai: TextView = view.findViewById(R.id.ishai_time)
    private var nextSalaah: TextView = view.findViewById(R.id.nextSalaah)

    lateinit var volleyRequest:VolleyRequest

    private lateinit var fajrTime:String
    private lateinit var thurTime:String
    private lateinit var asrTime:String
    private lateinit var magirebTime:String
    private lateinit var ishaiTime:String

    init {
//        volleyRequest = VolleyRequest(view)
//        volleyRequest.setDailyTimeCallbackListener(context)
    }

    fun setSalaahTimes() {
        volleyRequest.volleyRequestDaily()
        //TODO: Change to times from api
        if (!fajrTime.isNullOrBlank()) {
            fajr.text = fajrTime
            thur.text = thurTime
            asr.text = asrTime
            magrieb.text = magirebTime
            ishai.text = ishaiTime
        }
    }

    fun setNextTime() {
        nextSalaah.text = "Thur"
    }

    fun getCurrentTime() {
        Log.d("getCurrentTime: ", fajr.text.toString())
    }

    override fun displayFajrTime(fajr: Any) {
        fajrTime = fajr as String
    }

    override fun displayThurTime(thur: Any) {
        TODO("Not yet implemented")
    }

    override fun displayAsrTime(asr: Any) {
        TODO("Not yet implemented")
    }

    override fun displayMagirebTime(magrieb: Any) {
        TODO("Not yet implemented")
    }

    override fun displayIshaiTime(ishai: Any) {
        TODO("Not yet implemented")
    }

}