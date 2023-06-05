package com.ca.home.navigation

internal sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Settings : Screen("settings")
    object Insulin : Screen("insulin" ) {
        object Record : Screen("recordInsulin")
        object List : Screen("listInsulin")
        object Edit : Screen("editInsulin")
    }
    object Glucose : Screen("glucose") {
        object Record : Screen("recordGlucose")
        object List : Screen("listGlucose")
        object Edit : Screen("editGlucose")
    }
}