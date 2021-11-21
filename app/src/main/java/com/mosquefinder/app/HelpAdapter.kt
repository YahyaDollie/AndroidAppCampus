package com.mosquefinder.app

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mosquefinder.R
import com.mosquefinder.app.models.HelpItems


class HelpAdapter(var items:ArrayList<HelpItems>):
    RecyclerView.Adapter<HelpAdapter.ViewHolder>(){

    private var previousClickedPosition: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.help_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])

        holder.itemView.setOnClickListener{
            items[position].isVisible = !items[position].isVisible
            notifyItemChanged(position)
            if (previousClickedPosition != position) {
                closePreviousFaqUpdateTracking(position)
            }
            TransitionManager.beginDelayedTransition(holder.expandedMenu)
        }
    }

    private fun closePreviousFaqUpdateTracking(currentClickedFaqPosition: Int) {
        previousClickedPosition?.let {
            items[it].isVisible = false
            notifyItemChanged(it)
        }
        previousClickedPosition = currentClickedFaqPosition
    }

    override fun getItemCount(): Int {
        return items.size
    }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            val helpHeading : TextView = itemView.findViewById(R.id.faq_heading_text)
            val helpBody : TextView = itemView.findViewById(R.id.faq_body)
            val expandedMenu: ViewGroup = itemView.findViewById(R.id.expanded)

            fun onBind(helpList: HelpItems){
                helpHeading.text = helpList.heading
                helpBody.text = helpList.body
                expandedMenu.visibility = if (helpList.isVisible) View.VISIBLE else View.GONE
            }
        }
}