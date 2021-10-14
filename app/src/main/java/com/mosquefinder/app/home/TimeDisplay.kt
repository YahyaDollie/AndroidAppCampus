package com.mosquefinder.app.home

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.mosquefinder.R
import com.mosquefinder.app.api.Item
import com.mosquefinder.app.home.DataFromApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
This class handles the time displayed till the next Salaah time
 */
class TimeDisplay(view: View) : FrameLayout(view.context) {

    private val nextSalaahTime: TextView = view.findViewById(R.id.nextSalaah)

    private fun handleDisplayTime(time: String){
        nextSalaahTime.text = time
    }

}