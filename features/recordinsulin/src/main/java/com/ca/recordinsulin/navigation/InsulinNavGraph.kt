package com.ca.recordinsulin.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ca.recordinsulin.presentation.RecordInsulinScreen

private const val recordInsulinGraphRoute = "insulin_graph"
private const val recordInsulinRoute = "record_insulin"
private const val argumentName = "recordId"

fun NavController.navigateToRecordInsulin(recordId: String? = null) {
    navigate("$recordInsulinRoute?$argumentName=$recordId")
}

fun NavGraphBuilder.insulinGraph(
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = "$recordInsulinRoute?$argumentName={$argumentName}",
        route = recordInsulinGraphRoute
    ) {
        composable(
            route = "$recordInsulinRoute?$argumentName={$argumentName}",
            arguments = listOf(navArgument(argumentName) { nullable = true })
        ) {
            RecordInsulinScreen(
                navArgument = it.arguments?.getString(argumentName),
                onBackClick = { navigateBack() }
            )
        }
    }
}