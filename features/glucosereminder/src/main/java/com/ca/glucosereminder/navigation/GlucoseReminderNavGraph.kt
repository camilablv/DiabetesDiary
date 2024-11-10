package com.ca.glucosereminder.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.ca.glucosereminder.presentation.GlucoseReminderRoute
import kotlinx.serialization.Serializable


@Serializable
object GlucoseReminderGraph {
    @Serializable
    data class GlucoseReminder(val reminderId: Int? = null)
}

fun NavController.navigateToGlucoseReminder(reminderId: Int? = null) {
    navigate(GlucoseReminderGraph.GlucoseReminder(reminderId))
}

fun NavGraphBuilder.glucoseReminderGraph(
    navigateBack: () -> Unit
) {
    navigation<GlucoseReminderGraph>(
        startDestination = GlucoseReminderGraph.GlucoseReminder(),
    ) {
        composable<GlucoseReminderGraph.GlucoseReminder> {
            val reminderId = it.toRoute<GlucoseReminderGraph.GlucoseReminder>().reminderId
            GlucoseReminderRoute(
                navigateBack = navigateBack,
                reminderId = reminderId
            )
        }
    }
}