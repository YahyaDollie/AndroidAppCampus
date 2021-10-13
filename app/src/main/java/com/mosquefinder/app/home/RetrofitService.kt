package com.mosquefinder.app.home

import com.mosquefinder.app.api.SalaahTimes
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("{city}.json?key=e7e6e40fc282866c47cda3e819fc9f04")
    fun getApi(@Path("city")city: String): Call<SalaahTimes>

    companion object {
        fun create(): RetrofitService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://muslimsalat.com/")
                .build()
            return retrofit.create(RetrofitService::class.java)
        }
    }
}