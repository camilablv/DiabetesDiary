package com.ca.home.navigation

internal sealed class Route(val route: String) {
    object Home : Route("home")
    object Settings : Route("settings")
}