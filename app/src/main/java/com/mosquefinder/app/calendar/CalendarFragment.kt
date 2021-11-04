package com.mosquefinder.app.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mosquefinder.R
import com.google.gson.GsonBuilder
import com.mosquefinder.app.mosquefind.MasjidModel
import com.mosquefinder.app.network.Item
import com.mosquefinder.app.network.TimeModel
import com.mosquefinder.app.network.VolleyRequest
import org.json.JSONObject
import java.lang.IndexOutOfBoundsException
import java.text.SimpleDateFormat

class CalendarFragment : Fragment() {

    private lateinit var calendarView: View
    private lateinit var recyclerView: RecyclerView
    private var calendarList: ArrayList<Item> = ArrayList()
    private var adapter : CalendarAdapter = CalendarAdapter(calendarList)
    private lateinit var volleyRequest: VolleyRequest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calendarView = inflater.inflate(R.layout.fragment_calendar, container, false)
        return calendarView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        extractData()
    }

    private fun initRecyclerView(){
        recyclerView = calendarView.findViewById(R.id.calendar_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.items = calendarList
        adapter.notifyDataSetChanged()
    }

    private fun loadData() {
        adapter.items
        adapter.notifyDataSetChanged()
    }

    private fun extractData() {
        val url = "https://muslimsalat.com/monthly/cape-town.json?key=e7e6e40fc282866c47cda3e819fc9f04"
        val requestQueue = Volley.newRequestQueue(context)
        val jsonRequestObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                    response ->
                gsonParserMonth(response)
                initRecyclerView()
            }
            , {

            }
        )
        requestQueue.add(jsonRequestObjectRequest)
    }

    fun gsonParserMonth(response: JSONObject){
        val gson = GsonBuilder().create()
        val obj = gson.fromJson(response.toString(), MonthModel::class.java)

        calendarList = obj.items as ArrayList<Item>

        Log.d("gsonParserMonth: ", calendarList.toString())
    }

    fun getMonth(){
        val date = System.currentTimeMillis()

//        dateFormat = SimpleDateFormat("MMMMM yyyy")
    }
}