package com.mosquefinder.app.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mosquefinder.R
import com.mosquefinder.app.mosquefind.MasjidModel

class CalendarFragment : Fragment() {

    private lateinit var calendarView: View
    private lateinit var recyclerView: RecyclerView
    private var calendarList: ArrayList<CalendarModel> = ArrayList()
    private var adapter : CalendarAdapter = CalendarAdapter(calendarList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calendarView = inflater.inflate(R.layout.fragment_calendar, container, false)
        initRecyclerView()
        return calendarView
    }

    private fun initRecyclerView(){
        recyclerView = calendarView.findViewById(R.id.calendar_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        loadData()
    }
    private fun loadData() {
//        adapter.items = items as ArrayList<CalendarModel>
        adapter.notifyDataSetChanged()
    }
}