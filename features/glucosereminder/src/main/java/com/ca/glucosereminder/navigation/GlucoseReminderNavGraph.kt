package com.ca.glucosereminder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.glucosereminder.presentation.GlucoseReminderScreen

private const val glucoseReminderGraphRoute = "glucose_reminder_graph"
private const val glucoseReminderRoute = "glucose_reminder_route"

fun NavController.navigateToGlucoseReminder() {
    navigate(glucoseReminderRoute)
}

fun NavGraphBuilder.glucoseReminderGraph(
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = glucoseReminderRoute,
        route = glucoseReminderGraphRoute
    ) {
        composable(glucoseReminderRoute) {
            GlucoseReminderScreen(navigateBack = navigateBack)
        }
    }
}