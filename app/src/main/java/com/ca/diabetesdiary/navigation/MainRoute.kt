package com.ca.diabetesdiary.navigation


internal sealed class MainRoute(val route: String) {
    object Auth : MainRoute("auth")
    object OnBoarding : MainRoute("on_boarding")
    object Home : MainRoute("home")
}
