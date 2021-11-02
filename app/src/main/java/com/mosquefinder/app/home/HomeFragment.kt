package com.mosquefinder.app.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mosquefinder.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mosquefinder.app.network.VolleyRequest
import org.json.JSONObject
import java.util.*

/*
Main fragment for the home view
 */

class HomeFragment : Fragment(), CurrentTimeCallbackListener, DailyTimesCallbackListener, JSONResponseCallbackListener {
    private lateinit var homeView: View
    private lateinit var mapButton: ImageButton
    private lateinit var mosqueButton: ImageButton
    private lateinit var calendarButton: ImageButton
    private lateinit var navController: NavController
    private lateinit var volleyRequest: VolleyRequest
    private lateinit var fajrTime:String
    private lateinit var fajr_time:TextView
    private lateinit var thur_time:TextView
    private lateinit var asr_time:TextView
    private lateinit var magrieb_time:TextView
    private lateinit var ishai_time:TextView
    private lateinit var currentTime: CurrentTime
    private lateinit var broadcastHandler: BroadcastHandler
    private lateinit var salaahTimes: SalaahTimes
    private lateinit var homeNavigator: HomeNavigator
    private lateinit var spinnerDropdown: Spinner
    private lateinit var btn_show:ImageButton
    private lateinit var bottomSheetView:View
    private lateinit var bottomSheet : BottomSheetDialog

    val intentFilter = IntentFilter(Intent.ACTION_TIME_TICK)

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false)
        volleyRequest = VolleyRequest(homeView)
        currentTime = CurrentTime(homeView)
        salaahTimes = SalaahTimes(homeView)
        broadcastHandler = BroadcastHandler()
        spinnerDropdown = homeView.findViewById(R.id.location_spinner)
        var options = arrayOf("Cape Town")
        spinnerDropdown.adapter = ArrayAdapter(homeView.context, android.R.layout.simple_spinner_dropdown_item,options)
        spinnerDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        return homeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentTime.setInitalTime()
        this.navController = Navigation.findNavController(view)
        broadcastHandler.setCurrentTimeCallbackListener(this)
        volleyRequest.setDailyTimeCallbackListener(this)
        volleyRequest.setJSONResponseCallbackListener(this)
        volleyRequest.volleyRequestDaily()
        fajr_time = homeView.findViewById(R.id.fajr_time)
        thur_time = homeView.findViewById(R.id.thur_time)
        asr_time = homeView.findViewById(R.id.asr_time)
        magrieb_time = homeView.findViewById(R.id.magrieb_time)
        ishai_time = homeView.findViewById(R.id.ishai_time)
        registerReciver()
//        handleNavigation()
    }

    override fun onStart() {
        super.onStart()
//        salaahTimes.setSalaahTimes()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun registerReciver(){
        requireActivity().registerReceiver(broadcastHandler, intentFilter)
    }

    private fun unregisterReciver(){
        requireActivity().unregisterReceiver(broadcastHandler)
    }

    private fun displayTimes() {

    }

    override fun displayFajrTime(fajr: Any) {
        salaahTimes.fajrTime = fajr.toString()
    }

    override fun displayThurTime(thur: Any) {
        salaahTimes.thurTime = thur as String
    }

    override fun displayAsrTime(asr: Any) {
        salaahTimes.asrTime = asr as String
    }

    override fun displayMagirebTime(magrieb: Any) {
        salaahTimes.magirebTime = magrieb as String
    }

    override fun displayIshaiTime(ishai: Any) {
        salaahTimes.ishaiTime = ishai as String
    }

    override fun displayCurrentTime(time: Date) {
        currentTime.formatDate(time)
    }

    override fun gotResponse(array: JSONObject) {
        volleyRequest.gsonParser(array)
    }

}