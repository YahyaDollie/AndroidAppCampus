package com.mosquefinder.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mosquefinder.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mosquefinder.app.home.BroadcastHandler

class MainActivity : AppCompatActivity() {

    private lateinit var helpBtn: FloatingActionButton
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragment)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        bottomNavigationView.setupWithNavController(navController)

        bottomSheetDialog = BottomSheetDialog(this)

        helpBtn = findViewById(R.id.faq_btn)
        helpBtn.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }

        receiver = BroadcastHandler(application)
        registerReceiver(receiver, IntentFilter(Intent.ACTION_TIME_TICK))
    }

    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?): Intent? {
        return super.registerReceiver(receiver, filter)
    }
}