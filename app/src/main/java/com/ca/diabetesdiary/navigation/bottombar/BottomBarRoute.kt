package com.ca.diabetesdiary.navigation.bottombar


sealed class BottomBarRoute(val route: String) {
    object Home : BottomBarRoute("home")
    object Reminder : BottomBarRoute("reminder")
    object Settings : BottomBarRoute("settings")
    object Records: BottomBarRoute("records")
}