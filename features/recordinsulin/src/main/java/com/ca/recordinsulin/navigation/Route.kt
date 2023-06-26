package com.ca.recordinsulin.navigation

internal sealed class Route(val route: String) {
    object Record : Route("record_insulin")
}
