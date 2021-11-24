package com.mosquefinder.app.models

data class HelpItems(
    val heading: String,
    val body: String,
    var isVisible: Boolean = false,
)
