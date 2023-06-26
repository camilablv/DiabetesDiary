package com.ca.getstarted.navigation

internal sealed class Route(val route: String) {
    object Auth : Route("auth")
    object OnBoarding : Route("onBoarding")
}