package com.ca.diabetesdiary.navigation


internal sealed class Route(val route: String) {
    object Auth : Route("getStarted")
    object Home : Route("home")
}
