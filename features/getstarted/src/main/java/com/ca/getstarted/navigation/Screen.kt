package com.ca.getstarted.navigation

internal sealed class Screen(val route: String) {
    object OnBoarding : Screen("OnBoarding")
    object Auth : Screen("Auth")
}