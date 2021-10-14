package com.mosquefinder.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.mosquefinder.R
import com.mosquefinder.app.home.CurrentTime

class MainActivity : AppCompatActivity() {

//    lateinit var dataBaseHelper:DataBaseHelper
    private lateinit var navController: NavController
//    private lateinit var fajr_time:TextView
//    private lateinit var thur_time:TextView
//    private lateinit var asr_time:TextView
//    private lateinit var magrieb_time:TextView
//    private lateinit var ishai_time:TextView
//    private lateinit var timehour:TextView
//    private lateinit var timemin:TextView
//    private lateinit var nextSalaah:TextView
//    private var start: LocalTime = LocalTime.parse("08:00")
//    private var stop: LocalTime = LocalTime.parse("08:00")
//    private lateinit var duration:Duration
//    private lateinit var remainingTime:String
//    private val date = getCurrentDateTime()
//    private val  = date.toString("HH:mm")
//    private var currentTime: LocalTime = LocalTime.parse(dateInString)
    private lateinit var navigationManager: NavigationManager
    private lateinit var currentTime: CurrentTime

//    private val FAJR = "Fajr"
//    private val THUR = "Thur"
//    private val ASR = "Asr"
//    private val MAGRIEB = "Magrieb"
//    private val ISHAI = "Ishai"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.content_main)
//        navController = findNavController(R.id.nav_graph)
//        val intentFilter = IntentFilter(Intent.ACTION_TIME_TICK)
        setContentView(R.layout.activity_main)
//        registerReceiver(currentTime.timeBroadcastReceiver, intentFilter)
//
//        dataBaseHelper = DataBaseHelper(this)
//        dataBaseHelper.addTime()
//
//        fajr_time = findViewById(R.id.fajr_time)
//        thur_time = findViewById(R.id.thur_time)
//        asr_time = findViewById(R.id.asr_time)
//        magrieb_time = findViewById(R.id.magrieb_time)
//        ishai_time = findViewById(R.id.ishai_time)
//        timemin = findViewById(R.id.remainingTime)
//        nextSalaah = findViewById(R.id.nextSalaah)
//
//        fajr_time.text = dataBaseHelper.salaahTimes[0]
//        thur_time.text = dataBaseHelper.salaahTimes[1]
//        asr_time.text = dataBaseHelper.salaahTimes[2]
//        magrieb_time.text = dataBaseHelper.salaahTimes[3]
//        ishai_time.text = dataBaseHelper.salaahTimes[4]

//        DataFromApi(this).getDataFromApi("cape-town")

//        when (true) {
//            isBetween(4,0) -> nextSalaah.text = FAJR
//            isBetween(0,1) -> nextSalaah.text = THUR
//            isBetween(1,2) -> nextSalaah.text = ASR
//            isBetween(2,3) -> nextSalaah.text = MAGRIEB
//            isBetween(3,4) -> nextSalaah.text = ISHAI
//        }
//
//        when (nextSalaah.text){
//            FAJR -> timemin.text = displayRemainingTime(0)
//            THUR -> timemin.text = displayRemainingTime(1)
//            ASR -> timemin.text = displayRemainingTime(2)
//            MAGRIEB -> timemin.text = displayRemainingTime(3)
//            ISHAI -> timemin.text = displayRemainingTime(4)
//        }
    }
//
//    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
//        val formatter = SimpleDateFormat(format, locale)
//        return formatter.format(this)
//    }
//
//    private fun getCurrentDateTime(): Date {
//        return Calendar.getInstance().time
//    }
//
//    private fun displayRemainingTime(endIndex: Int): String? {
//        var nextSalaahTime:LocalTime = LocalTime.parse(dataBaseHelper.salaahTimes[endIndex])
//
//        if (Duration.between(currentTime, nextSalaahTime).isNegative){
//            return null
//        } else {
//            remainingTime = Duration.between(currentTime, nextSalaahTime).toString()
//            remainingTime = if (calculateMinRemaning() != null) {
//                calculateHoursRemaining() + "H and " + calculateMinRemaning() + "m is remaining till:"
//            } else {
//                calculateHoursRemaining() + "m remaining till"
//            }
//
//            return remainingTime
//        }
//    }
//
//    private fun isBetween(startIndex:Int, endIndex:Int): Boolean{
//        val previousSalaahTime: LocalTime = LocalTime.parse(dataBaseHelper.salaahTimes[startIndex])
//        val nextSalaahTime: LocalTime = LocalTime.parse(dataBaseHelper.salaahTimes[endIndex])
//
//        if (endIndex == 0){
//            return currentTime.isAfter(previousSalaahTime)
//        } else {
//            return currentTime.isBefore(nextSalaahTime) && currentTime.isAfter(previousSalaahTime)
//        }
//    }
//
//    private fun calculateHoursRemaining(): String{
//        var firstHourDigit:Char?
//        var secondHourDigit:Char?
//        val hourRemaining:String?
//
//        if (remainingTime[2].isDigit() && remainingTime[3].isDigit()) {
//            firstHourDigit = remainingTime[2]
//            secondHourDigit = remainingTime[3]
//            hourRemaining = firstHourDigit.toString() + secondHourDigit.toString()
//            return hourRemaining
//        } else {
//            firstHourDigit = remainingTime[2]
//            hourRemaining = firstHourDigit.toString()
//            return hourRemaining
//        }
//    }
//
//    private fun calculateMinRemaning(): String? {
//        var firstMinDigit: Char?
//        var secondMinDigit: Char?
//        var minRemaining: String?
//        try {
//            if (remainingTime[4].isDigit() && !remainingTime[5].isDigit()) {
//                firstMinDigit = remainingTime[4]
//                minRemaining = firstMinDigit.toString()
//                return minRemaining
//            } else if (remainingTime[4].isDigit() && remainingTime[5].isDigit()) {
//                firstMinDigit = remainingTime[4]
//                secondMinDigit = remainingTime[5]
//                minRemaining = firstMinDigit.toString() + secondMinDigit.toString()
//                return minRemaining
//            } else if (!remainingTime[4].isDigit() && remainingTime[5].isDigit() && !remainingTime[6].isDigit()) {
//                firstMinDigit = remainingTime[5]
//                minRemaining = firstMinDigit.toString()
//                return minRemaining
//            } else if (remainingTime[5].isDigit() && remainingTime[6].isDigit()) {
//                firstMinDigit = remainingTime[5]
//                secondMinDigit = remainingTime[6]
//                minRemaining = firstMinDigit.toString() + secondMinDigit.toString()
//                return minRemaining
//            } else {
//                minRemaining = null
//                return minRemaining
//            }
//        } catch (e: StringIndexOutOfBoundsException) {
//            Log.d(
//                e.toString(),
//                "Indication of no hour value present therefore we can use the hour calculation function to display minuites"
//            )
//            minRemaining = null
//            return minRemaining
//        }
//    }

//    override fun onDataCompleteFromApi(salaah: Item) {
//        fajr_time.text = salaah.fajr
//        thur_time.text = salaah.dhuhr
//        asr_time.text = salaah.asr
//        magrieb_time.text = salaah.maghrib
//        ishai_time.text = salaah.isha
//
//    }
//
//    override fun onDataErrorFromApi(throwable: Throwable) {
//        error("error ---------> ${throwable.localizedMessage}")
//    }
}