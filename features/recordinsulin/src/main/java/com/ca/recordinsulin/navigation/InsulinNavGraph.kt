package com.ca.recordinsulin.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ca.recordinsulin.presentation.RecordInsulinRoute

private const val recordInsulinGraphRoute = "insulin_graph"
private const val recordInsulinRoute = "record_insulin"
private const val argumentName = "recordId"

fun NavController.navigateToRecordInsulin(recordId: String? = null) {
    var route = recordInsulinRoute
    recordId?.let {
        route = route.plus("?$argumentName=$it")
    }
    navigate(route)
}

fun NavGraphBuilder.insulinGraph(
    navigateBack: () -> Unit,
    navigateToSettings: () -> Unit
) {
    navigation(
        startDestination = "$recordInsulinRoute?$argumentName={$argumentName}",
        route = recordInsulinGraphRoute
    ) {
        composable(
            route = "$recordInsulinRoute?$argumentName={$argumentName}",
            arguments = listOf(navArgument(argumentName) { nullable = true })
        ) {
            val recordId = it.arguments?.getString(argumentName)
            RecordInsulinRoute(
                recordId = recordId,
                onBackClick = { navigateBack() },
                navigateToSettings = { navigateToSettings() }
            )
        }
    }
}