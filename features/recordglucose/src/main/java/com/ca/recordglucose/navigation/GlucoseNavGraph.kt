package com.ca.recordglucose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ca.recordglucose.presentation.RecordGlucoseScreen

private const val glucoseGraphRoute = "glucose_graph"

fun NavController.navigateToRecordGlucose() {
    navigate(Route.Record.route)
}

fun NavGraphBuilder.glucoseGraph() {
    navigation(
        startDestination = Route.Record.route,
        route = glucoseGraphRoute
    ) {
        composable(Route.Record.route) {
            RecordGlucoseScreen()
        }
    }
}