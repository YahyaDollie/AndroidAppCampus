package com.mosquefinder.app

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mosquefinder.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var dataBaseHelper:DataBaseHelper
    private lateinit var fajr_time:TextView
    private lateinit var thur_time:TextView
    private lateinit var asr_time:TextView
    private lateinit var magrieb_time:TextView
    private lateinit var ishai_time:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBaseHelper = DataBaseHelper(this)
        dataBaseHelper.addTime()

        fajr_time = findViewById(R.id.fajr_time)
        thur_time = findViewById(R.id.thur_time)
        asr_time = findViewById(R.id.asr_time)
        magrieb_time = findViewById(R.id.magrieb_time)
        ishai_time = findViewById(R.id.ishai_time)

        fajr_time.text = dataBaseHelper.salaahTimes[0]
        thur_time.text = dataBaseHelper.salaahTimes[1]
        asr_time.text = dataBaseHelper.salaahTimes[2]
        magrieb_time.text = dataBaseHelper.salaahTimes[1]
        ishai_time.text = dataBaseHelper.salaahTimes[2]
    }
}