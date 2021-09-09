package com.mosquefinder.app

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class ViewModelLiveData : ViewModel() {

    private val START_TIME_IN_MILLIS = 600000L
    private lateinit var mCountDownTimer: CountDownTimer
    private val _seconds = MutableLiveData<String>()
    private var mTimerRunning: Boolean = false;
    private var mTimeLeftInMillis = START_TIME_IN_MILLIS
    private  val _time = MutableLiveData<String>()

    fun startTimer(){
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                TODO("Not yet implemented")
                mTimerRunning = false
            }

        }.start()

        mTimerRunning = true
    }

    fun pauseTimer(){

    }

    fun seconds(): LiveData<String>{
        return _time
    }

    fun resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS
        updateCountDownText()
    }

    fun updateCountDownText() {
        var minutes = ((mTimeLeftInMillis / 1000) / 60).toInt()
        var seconds = ((mTimeLeftInMillis / 1000) % 60).toInt()

        var timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds)

        _time.value = timeLeftFormatted
    }

}