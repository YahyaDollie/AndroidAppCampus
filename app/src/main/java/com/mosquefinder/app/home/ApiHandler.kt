package com.mosquefinder.app.home

import com.mosquefinder.app.api.Item

interface ApiHandler {
    fun onDataCompleteFromApi(salaah: Item)
    fun onDataErrorFromApi(throwable: Throwable)
}