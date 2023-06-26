package com.ca.authentication.navigation

internal sealed class Route(val route: String) {
    object Auth : Route("auth")
    object OnBoarding : Route("on_boarding")
}