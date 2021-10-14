package com.mosquefinder.app.mosquefind

import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton

data class Items(

    var container: ImageView,

    var title: String,

    var area: String,

    var img: ImageView,

    var location: FloatingActionButton
){

}