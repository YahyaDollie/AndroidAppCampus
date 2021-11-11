package com.mosquefinder.app

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mosquefinder.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var recyclerView: RecyclerView
    private var helpList = arrayListOf(
        HelpItems("Home Screen", "The home screen displays the daily salaah times. It also includes the time left till the next salaah and the gives a display of the time according to your timezone."),
        HelpItems("Maps", "The maps screen opens an instance of Google Maps showing your location and from there you can locate a nearby masjid."),
        HelpItems("Calendar", "The calendar screen displays the salaah times for the remaining days of the current month."),
        HelpItems("MosqueList", "The mosques screen displays a list of masjids in and around Cape Town")
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