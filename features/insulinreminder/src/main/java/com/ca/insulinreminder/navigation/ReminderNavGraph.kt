package com.ca.insulinreminder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ca.insulinreminder.presentation.InsulinReminderScreen

private const val insulinReminderGraphRoute = "insulin_reminder_graph"
private const val insulinReminderRoute = "insulin_reminder"
private const val argumentName = "reminderId"

fun NavController.navigateToInsulinReminder(reminderId: Int? = null) {
    navigate("$insulinReminderRoute?$argumentName=$reminderId")
}

fun NavGraphBuilder.insulinReminderGraph(
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = "$insulinReminderRoute?$argumentName={$argumentName}",
        route = insulinReminderGraphRoute
    ) {
        composable(
            route = "$insulinReminderRoute?$argumentName={$argumentName}",
            arguments = listOf(navArgument(argumentName) { nullable = true })
        ) {
            val reminderId = it.arguments?.getString(argumentName)?.toInt()
            InsulinReminderScreen(
                reminderId = reminderId,
                navigateBack = navigateBack
            )
        }
    }
}