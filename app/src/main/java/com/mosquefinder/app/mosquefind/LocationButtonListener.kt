package com.mosquefinder.app.mosquefind

import android.view.View

interface LocationButtonListener {
    fun locationButtonClicked(geoLocation: String, view: View)
}