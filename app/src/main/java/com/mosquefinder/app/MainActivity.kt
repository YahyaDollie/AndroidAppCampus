package com.mosquefinder.app

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mosquefinder.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var countdown:TextView;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countdown = findViewById(R.id.timeRemaining)

        val viewModel = ViewModelProvider(this).get(ViewModelLiveData::class.java)
        viewModel.startTimer()
        viewModel.seconds().observe(this, androidx.lifecycle.Observer {
            countdown.text = it
        })

    }
}