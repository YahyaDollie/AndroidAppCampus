package com.mosquefinder.app.home

interface DailyTimesCallbackListener {
    fun displayFajrTime(fajr: Any)
    fun displayThurTime(thur: Any)
    fun displayAsrTime(asr: Any)
    fun displayMagirebTime(magrieb: Any)
    fun displayIshaiTime(ishai: Any)
}