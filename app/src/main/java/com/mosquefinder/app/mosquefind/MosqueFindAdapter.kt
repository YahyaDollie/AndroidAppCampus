package com.mosquefinder.app.mosquefind

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mosquefinder.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mosquefinder.app.models.MasjidModel

internal class  MosqueFindAdapter(var items: List<MasjidModel>):
    RecyclerView.Adapter<MosqueFindAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val locationButtonListener : LocationButtonListener = MosqueFindFragment()

        fun bind(masjidModel: MasjidModel){
            val masjidName = itemView.findViewById<TextView>(R.id.masjid_title)
            masjidName.text = masjidModel.mosqueTitle
            val masjidArea = itemView.findViewById<TextView>(R.id.address)
            masjidArea.text = masjidModel.area
            val masjidimg = itemView.findViewById<ImageView>(R.id.masjid_img)
            Glide.with(itemView.context).load(masjidModel.img).into(masjidimg)
            masjidimg.clipToOutline = true

            val locationButton : FloatingActionButton = itemView.findViewById(R.id.locationBtn)

            locationButton.setOnClickListener {
                locationButtonListener.locationButtonClicked(masjidModel.geoLocation, itemView)
            }
        }
    }

}