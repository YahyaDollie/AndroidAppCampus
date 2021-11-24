package com.mosquefinder.app.calendar

import android.annotation.SuppressLint
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mosquefinder.R
import com.mosquefinder.app.models.Item
import java.text.DateFormat
import java.text.SimpleDateFormat

class CalendarAdapter(var items: List<Item>) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.calendar_items, parent, false)
        TransitionManager.beginDelayedTransition(parent)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
        Log.d("onBindViewHolder: ", items.toString())
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var date: TextView = itemView.findViewById(R.id.date_english)
        var fajr: TextView = itemView.findViewById(R.id.fajr_calendar)
        var thur: TextView = itemView.findViewById(R.id.thur_calendar)
        var asr: TextView = itemView.findViewById(R.id.asr_calendar)
        var magrieb: TextView = itemView.findViewById(R.id.magrieb_calendar)
        var ishai: TextView = itemView.findViewById(R.id.ishai_calendar)

        @SuppressLint("SimpleDateFormat")
        fun onBind(calendarModel: Item) {
            var timeFormat: DateFormat = SimpleDateFormat("hh:mm aa")
            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")

            var outputTimeFormat: DateFormat? = SimpleDateFormat("HH:mm")
            val outputDateFormat: DateFormat = SimpleDateFormat("dd")

            date.text = outputDateFormat.format(dateFormat.parse(calendarModel.date_for))
            fajr.text = outputTimeFormat?.format(timeFormat.parse(calendarModel.fajr))
            thur.text = outputTimeFormat?.format(timeFormat.parse(calendarModel.dhuhr))
            asr.text = outputTimeFormat?.format(timeFormat.parse(calendarModel.asr))
            magrieb.text = outputTimeFormat?.format(timeFormat.parse(calendarModel.maghrib))
            ishai.text = outputTimeFormat?.format(timeFormat.parse(calendarModel.isha))
        }

    }
}