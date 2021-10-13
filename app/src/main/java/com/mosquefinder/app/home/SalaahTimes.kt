package com.mosquefinder.app.home

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.mosquefinder.R
import com.mosquefinder.app.api.Item

/*
This class sets the time for each Salaah, gets the times from the API and calculates time till next salaah
 */

class SalaahTimes(view: View) : FrameLayout(view.context), ApiHandler {

    private var fajr: TextView = view.findViewById(R.id.fajr_time)
    private var thur: TextView = view.findViewById(R.id.thur_time)
    private var asr: TextView = view.findViewById(R.id.asr_time)
    private var magrieb: TextView = view.findViewById(R.id.magrieb_time)
    private var ishai: TextView = view.findViewById(R.id.ishai_time)

    private lateinit var fajrTime:String
    private lateinit var thurTime:String
    private lateinit var asrTime:String
    private lateinit var magirebTime:String
    private lateinit var ishaiTime:String

    private var dateFromApi: DataFromApi = DataFromApi(this)

    init {
        dateFromApi.getDataFromApi("cape-town")
    }

    override fun onDataCompleteFromApi(salaah: Item) {
        fajrTime = salaah.fajr
        thurTime = salaah.dhuhr
        asrTime = salaah.asr
        magirebTime = salaah.maghrib
        ishaiTime = salaah.isha
        setSalaahTimes()
    }

    override fun onDataErrorFromApi(throwable: Throwable) {
        error("error ---------> ${throwable.localizedMessage}")
    }

    fun setSalaahTimes() {
        //TODO: Change to times from api
        fajr.text = fajrTime
        thur.text = thurTime
        asr.text = asrTime
        magrieb.text = magirebTime
        ishai.text = ishaiTime
    }

    fun setNextTime() {

    }

}