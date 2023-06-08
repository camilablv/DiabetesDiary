package com.ca.getstarted.navigation

internal sealed class Route(val route: String) {
    object GetStarted : Route("getStarted")
    object OnBoarding : Route("onBoarding")
    object Auth : Route("auth")

}