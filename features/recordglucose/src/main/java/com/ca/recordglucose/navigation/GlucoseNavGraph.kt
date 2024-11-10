package com.ca.recordglucose.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ca.recordglucose.presentation.RecordGlucoseRoute
import kotlinx.serialization.Serializable

@Serializable
data object RecordGlucoseGraph {
    @Serializable
    data class RecordGlucose(val recordId: String? = null)
}

fun NavController.navigateToRecordGlucose(recordId: String?) {
    navigate(RecordGlucoseGraph.RecordGlucose(recordId))
}

fun NavGraphBuilder.glucoseGraph(
    navigateBack: () -> Unit
) {
    navigation<RecordGlucoseGraph>(
        startDestination = RecordGlucoseGraph.RecordGlucose(),
    ) {
        composable<RecordGlucoseGraph.RecordGlucose> {
            val recordId = it.toRoute<RecordGlucoseGraph.RecordGlucose>().recordId
            RecordGlucoseRoute(
                recordId = recordId,
                onBackClick = navigateBack
            )
        }
    }
}