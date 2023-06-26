package com.ca.recordglucose.navigation

internal sealed class Route(val route: String) {
    object Record : Route("record_glucose")
}
