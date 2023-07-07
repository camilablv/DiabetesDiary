package com.ca.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.model.GlucoseUnits

@Composable
fun ChooseGlucoseUnitCard(
    modifier: Modifier,
    units:  List<GlucoseUnits>,
    defaultUnit: GlucoseUnits,
    onSelect: (GlucoseUnits) -> Unit
) {
    CardWithTitle(
        modifier = modifier,
        title = "Choose Glucose Unit"
    ) {
        GlucoseUnitsRadioButtons(
            modifier = Modifier
                .padding(8.dp),
            units = units,
            defaultUnit = defaultUnit,
            onSelect = onSelect
        )
    }
}