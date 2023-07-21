package com.ca.insulinreminder.navigation

internal sealed class Route(val route: String) {
    object InsulinReminder : Route("insulin_reminder")
}
