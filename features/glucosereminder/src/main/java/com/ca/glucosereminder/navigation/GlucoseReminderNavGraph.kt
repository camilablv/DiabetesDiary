package com.ca.glucosereminder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.glucosereminder.presentation.GlucoseReminderRoute

private const val glucoseReminderGraphRoute = "glucose_reminder_graph"
private const val glucoseReminderRoute = "glucose_reminder_route"
private const val argumentName = "reminderId"

fun NavController.navigateToGlucoseReminder(reminderId: Int? = null) {
    navigate("$glucoseReminderRoute?$argumentName=$reminderId")
}

fun NavGraphBuilder.glucoseReminderGraph(
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = "$glucoseReminderRoute?$argumentName={$argumentName}",
        route = glucoseReminderGraphRoute
    ) {
        composable("$glucoseReminderRoute?$argumentName={$argumentName}") {
            val reminderId = it.arguments?.getString(argumentName)?.toIntOrNull()
            GlucoseReminderRoute(
                navigateBack = navigateBack,
                reminderId = reminderId
            )
        }
    }
}