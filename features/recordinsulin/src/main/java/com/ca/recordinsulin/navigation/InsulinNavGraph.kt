package com.ca.recordinsulin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.recordinsulin.presentation.RecordInsulinScreen

private const val insulinGraphRoute = "insulin_graph"

fun NavController.navigateToRecordInsulin() {
    navigate(Route.Record.route)
}

fun NavGraphBuilder.insulinGraph(
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = Route.Record.route,
        route = insulinGraphRoute
    ) {
        composable(Route.Record.route) {
            RecordInsulinScreen(
                onBackClick = { navigateBack() }
            )
        }
    }
}