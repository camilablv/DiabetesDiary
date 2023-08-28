package com.ca.recordglucose.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ca.recordglucose.presentation.RecordGlucoseRoute

private const val recordGlucoseGraphRoute = "glucose_graph"
private const val recordGlucoseRoute = "record_glucose"
private const val argumentName = "recordId"

fun NavController.navigateToRecordGlucose(recordId: String? = null) {
    var route = recordGlucoseRoute
    recordId?.let {
        route = route.plus("?$argumentName=$it")
    }
    navigate(route)
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
            val recordId = it.arguments?.getString(argumentName)
            RecordGlucoseRoute(
                recordId = recordId,
                onBackClick = navigateBack
            )
        }
    }
}