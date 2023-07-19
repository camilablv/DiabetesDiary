package com.ca.home.navigation

internal sealed class Route(val route: String) {
    object Home : Route("home")
    object Reminder : Route("reminder")
    object Settings : Route("settings")
    object Records: Route("records")
}