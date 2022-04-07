package com.mosquefinder.app.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mosquefinder.R
import com.google.gson.GsonBuilder
import com.mosquefinder.app.Volley.VolleyInterface
import com.mosquefinder.app.Volley.VolleyRequest
import com.mosquefinder.app.models.Item
import com.mosquefinder.app.models.MonthModel
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : Fragment(), VolleyInterface {

    private lateinit var calendarView: View
    private lateinit var recyclerView: RecyclerView
    private var calendarList: ArrayList<Item> = ArrayList()
    private var adapter: CalendarAdapter = CalendarAdapter(calendarList)
    val url = "https://muslimsalat.com/monthly/cape-town.json?key=e7e6e40fc282866c47cda3e819fc9f04"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        calendarView = inflater.inflate(R.layout.fragment_calendar, container, false)
        val volleyRequest = VolleyRequest.getInstance(context, this)
        volleyRequest.getRequest(url)
        return calendarView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        monthFromDate()
    }

    override fun onPause() {
        super.onPause()
        VolleyRequest.resetInstance()
    }

    private fun initRecyclerView() {
        recyclerView = calendarView.findViewById(R.id.calendar_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context,
            DividerItemDecoration.VERTICAL))
        recyclerView.adapter = adapter
        adapter.items = calendarList
        adapter.notifyDataSetChanged()
    }

    private fun gsonParserMonth(response: String) {
        val gson = GsonBuilder().create()
        val obj = gson.fromJson(response, MonthModel::class.java)
        calendarList = obj.items as ArrayList<Item>

        Log.d("gsonParserMonth: ", calendarList.toString())
    }

    private fun monthFromDate() {
        val calendar = Calendar.getInstance()
        val month: TextView = calendarView.findViewById(R.id.month)
        month.text = SimpleDateFormat("MMMM").format(calendar.time)
    }

    override fun onResponse(response: String) {
        gsonParserMonth(response)
        Log.d("onResponse: ",  "1")
//        initRecyclerView()
    }
}