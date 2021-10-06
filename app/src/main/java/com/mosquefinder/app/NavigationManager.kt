package com.mosquefinder.app

import androidx.navigation.NavController
import com.example.mosquefinder.R

internal class NavigationManager {

    fun navigateToCalendarFragment(navController: NavController) {
        navController.navigate(R.id.calendarFragment)
    }

    fun navigateToMapFragment(navController: NavController) {
        navController.navigate(R.id.mapsFragment)
    }
}