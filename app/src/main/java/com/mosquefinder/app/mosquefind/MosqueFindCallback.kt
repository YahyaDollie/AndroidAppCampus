package com.mosquefinder.app.mosquefind

import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

interface MosqueFindCallback {
    fun onMosqueFindItemClick(pos:Int,
                              container:ImageView,
                              title:TextView,
                              area:TextView,
                              img:ImageView,
                              location:FloatingActionButton)
}