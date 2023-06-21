package com.ca.getstarted.navigation

internal sealed class Route(val route: String) {
    object Welcome : Route("welcome")
    object OnBoarding : Route("onBoarding")
    object Auth : Route("auth")

}