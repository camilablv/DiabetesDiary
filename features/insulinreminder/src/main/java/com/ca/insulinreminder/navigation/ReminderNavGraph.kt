package com.ca.insulinreminder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.ca.insulinreminder.presentation.InsulinReminderRoute
import kotlinx.serialization.Serializable


@Serializable
data object InsulinReminderGraph {
    @Serializable
    data class InsulinReminder(val reminderId: Int? = null)
}

fun NavController.navigateToInsulinReminder(reminderId: Int? = null) {
    navigate(InsulinReminderGraph.InsulinReminder(reminderId))
}

fun NavGraphBuilder.insulinReminderGraph(
    navigateBack: () -> Unit,
    navigateToSettings: () -> Unit
) {
    navigation<InsulinReminderGraph>(
        startDestination = InsulinReminderGraph.InsulinReminder(),
    ) {
        composable<InsulinReminderGraph.InsulinReminder> {
            val reminderId = it.toRoute<InsulinReminderGraph.InsulinReminder>().reminderId
            InsulinReminderRoute(
                reminderId = reminderId,
                navigateBack = navigateBack,
                navigateToSettings = navigateToSettings
            )
        }
    }
}