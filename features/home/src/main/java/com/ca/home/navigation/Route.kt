package com.ca.home.navigation

internal sealed class Route(val route: String) {
    object Home : Route("home")
    object Settings : Route("settings")
    object Insulin : Route("insulin" ) {
        object Record : Route("recordInsulin")
        object List : Route("listInsulin")
        object Edit : Route("editInsulin")
    }
    object Glucose : Route("glucose") {
        object Record : Route("recordGlucose")
        object List : Route("listGlucose")
        object Edit : Route("editGlucose")
    }
}