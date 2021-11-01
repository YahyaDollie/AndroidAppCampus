package com.mosquefinder.app.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mosquefinder.R
import com.mosquefinder.app.mosquefind.MosqueFindAdapter

class CalendarAdapter(var items: List<CalendarModel>):
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>(){


        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bind(calendarModel: CalendarModel){

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.calendar_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}