package com.ca.diabetesdiary.navigation


internal sealed class Route(val route: String) {
    object Auth : Route("auth")
    object OnBoarding : Route("on_boarding")
    object Home : Route("home")
}
