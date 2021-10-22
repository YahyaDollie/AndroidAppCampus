package com.mosquefinder.app.home

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.mosquefinder.R
import com.mosquefinder.app.network.GSONRequest
import com.mosquefinder.app.network.Item

/*
This class handles the time displayed till the next Salaah time
 */
class TimeDisplay(view: View) : FrameLayout(view.context) {

    private val nextSalaahTime: TextView = view.findViewById(R.id.nextSalaah)
    var items:List<Item> = ArrayList<Item>()

    private fun handleDisplayTime(time: String){
        nextSalaahTime.text = time
    }

}

