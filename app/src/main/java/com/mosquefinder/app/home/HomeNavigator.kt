package com.mosquefinder.app.home

import androidx.navigation.NavController

interface HomeNavigator {
    fun handleCalendarNavigation(navController: NavController)
    fun handleMapNavigation(navController: NavController)
}