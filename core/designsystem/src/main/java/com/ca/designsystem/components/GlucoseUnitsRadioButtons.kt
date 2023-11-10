package com.ca.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ca.model.GlucoseUnits

@Composable
fun GlucoseUnitsRadioButtons(
    modifier: Modifier,
    selectedOption: GlucoseUnits,
    onSelect: (GlucoseUnits) -> Unit
) {
    Options(
        modifier = modifier,
        options = GlucoseUnits.units,
        selectedOption = selectedOption,
        onSelect = onSelect
    )
}