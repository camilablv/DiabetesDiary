package com.ca.recordinsulin.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ca.recordinsulin.presentation.RecordInsulinRoute
import kotlinx.serialization.Serializable

@Serializable
data object RecordInsulinGraph {
    @Serializable
    data class RecordInsulin(val recordId: String? = null)
}

fun NavController.navigateToRecordInsulin(recordId: String?) {
    navigate(RecordInsulinGraph.RecordInsulin(recordId))
}

fun NavGraphBuilder.insulinGraph(
    navigateBack: () -> Unit,
    navigateToSettings: () -> Unit
) {
    navigation<RecordInsulinGraph>(
        startDestination = RecordInsulinGraph.RecordInsulin(),
    ) {
        composable<RecordInsulinGraph.RecordInsulin> {
            val recordId = it.toRoute<RecordInsulinGraph.RecordInsulin>().recordId
            RecordInsulinRoute(
                recordId = recordId,
                onBackClick = { navigateBack() },
                navigateToSettings = { navigateToSettings() }
            )
        }
    }
}