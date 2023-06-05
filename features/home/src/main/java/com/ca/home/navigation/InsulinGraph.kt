package com.ca.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.recordinsulin.presentation.RecordInsulinScreen

fun NavGraphBuilder.insulinGraph() {
    navigation(
        startDestination = Route.Insulin.Record.route,
        route = Route.Insulin.route
    ) {
        composable(Route.Insulin.Record.route) {
            RecordInsulinScreen()
        }
        composable(Route.Insulin.List.route) {
            RecordInsulinScreen()
        }
    }
}