package com.ca.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.recordglucose.presentation.RecordGlucoseScreen

internal fun NavGraphBuilder.glucoseGraph() {
    navigation(
        startDestination = Route.Glucose.Record.route,
        route = Route.Glucose.route
    ) {
        composable(Route.Glucose.Record.route) {
            RecordGlucoseScreen()
        }
        composable(Route.Glucose.List.route) {
            RecordGlucoseScreen()
        }
    }
}