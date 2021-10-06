package com.mosquefinder.app

import android.content.Context
import com.mosquefinder.app.api.Item
import com.mosquefinder.app.api.SalaahTimes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataFromApi(context: Context) {
    val salaahTimes = context as ApiHandler

    fun getDataFromApi(city: String){
        RetrofitService.create()
            .getApi(city)
            .enqueue(object : Callback<SalaahTimes> {
                override fun onResponse(call: Call<SalaahTimes>, response: Response<SalaahTimes>) {
                    salaahTimes.onDataCompleteFromApi(response.body()?.items?.get(0) as Item)
                }

                override fun onFailure(call: Call<SalaahTimes>, t: Throwable) {
                    salaahTimes.onDataErrorFromApi(t)
                }

            })
    }
}