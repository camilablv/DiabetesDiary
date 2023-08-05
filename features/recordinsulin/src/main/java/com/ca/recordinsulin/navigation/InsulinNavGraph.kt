package com.ca.recordinsulin.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ca.recordinsulin.presentation.RecordInsulinScreen

private const val insulinGraphRoute = "insulin_graph"
private const val argumentName = "recordId"

fun NavController.navigateToRecordInsulin(recordId: String? = null) {
    navigate("${Route.Record.route}/$recordId")
}

fun NavGraphBuilder.insulinGraph(
    navigateBack: () -> Unit
) {
    navigation(
        startDestination = "${Route.Record.route}/{$argumentName}",
        route = insulinGraphRoute
    ) {
        composable(
            route = "${Route.Record.route}?$argumentName={$argumentName}",
            arguments = listOf(navArgument(argumentName) { type = NavType.StringType })
        ) {
            RecordInsulinScreen(
                navArgument = it.arguments?.getString(argumentName),
                onBackClick = { navigateBack() }
            )
        }
    }
}