package com.ca.insulinreminder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.insulinreminder.presentation.InsulinReminderScreen

private const val insulinReminderGraphRoute = "insulin_reminder_graph"

fun NavController.navigateToInsulinReminder() {
    navigate(Route.InsulinReminder.route)
}

fun NavGraphBuilder.insulinReminderGraph(
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = Route.InsulinReminder.route,
        route = insulinReminderGraphRoute
    ) {
        composable(Route.InsulinReminder.route) {
            InsulinReminderScreen(navigateBack = navigateBack)
        }
    }
}