package com.ca.designsystem.components.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ca.designsystem.components.GlucoseUnitsRadioButtons
import com.ca.model.GlucoseUnits
import com.ca.settings.presentation.components.SettingsSectionCard

@Composable
fun GlucoseUnitsSection(
    selectedUnits: GlucoseUnits,
    onSelect: (GlucoseUnits) -> Unit
) {
    SettingsSectionCard(
        modifier = Modifier,
        sectionTitle = "Choose glucose units"
    ) {
        GlucoseUnitsRadioButtons(
            modifier = Modifier,
            selectedOption = selectedUnits,
            onSelect = { onSelect(it) }
        )
    }
}