package com.ca.recordglucose.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ca.recordglucose.presentation.RecordGlucoseScreen

private const val recordGlucoseGraphRoute = "glucose_graph"
private const val recordGlucoseRoute = "record_glucose"
private const val argumentName = "recordId"

fun NavController.navigateToRecordGlucose(recordId: String? = null) {
    navigate("$recordGlucoseRoute?$argumentName=$recordId")
}

fun NavGraphBuilder.glucoseGraph(
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = "$recordGlucoseRoute?$argumentName={$argumentName}",
        route = recordGlucoseGraphRoute
    ) {
        composable(
            route = "$recordGlucoseRoute?$argumentName={$argumentName}",
            arguments = listOf(navArgument(argumentName) { nullable = true })
        ) {
            RecordGlucoseScreen(
                navArgument = it.arguments?.getString(argumentName),
                onBackClick = navigateBack
            )
        }
    }
}