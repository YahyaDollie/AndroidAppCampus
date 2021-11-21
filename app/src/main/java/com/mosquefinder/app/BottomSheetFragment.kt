package com.mosquefinder.app

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mosquefinder.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mosquefinder.app.models.HelpItems

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var recyclerView: RecyclerView
    private var helpList = arrayListOf(
        HelpItems("Home Screen", "The home screen displays the daily salaah times. It also includes the time left till the next salaah and the gives a display of the time according to your timezone."),
        HelpItems("Calendar", "The calendar screen displays the salaah times for the remaining days of the current month."),
        HelpItems("Masjid", "The masjid screen displays a list of masjids. clicking on the icon on the right of the masjid will open the location on Google Maps.")
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState)
        val bottomSheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_help,null)
        bottomSheetDialog.setContentView(bottomSheetView)

        recyclerView = bottomSheetView.findViewById(R.id.help_rv)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = HelpAdapter(helpList)

        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        return bottomSheetDialog
    }
}