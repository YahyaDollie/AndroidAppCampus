package com.mosquefinder.app.home

import com.mosquefinder.app.api.Item
import com.mosquefinder.app.api.SalaahTimes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataFromApi(context: com.mosquefinder.app.home.SalaahTimes) {
    val salaahView = context as ApiHandler

    fun getDataFromApi(city: String){
        RetrofitService.create()
            .getApi(city)
            .enqueue(object : Callback<SalaahTimes> {
                override fun onResponse(call: Call<SalaahTimes>, response: Response<SalaahTimes>) {
                    salaahView.onDataCompleteFromApi(response.body()?.items?.get(0) as Item)
                }

                override fun onFailure(call: Call<SalaahTimes>, t: Throwable) {
                    salaahView.onDataErrorFromApi(t)
                }

            })
    }
}