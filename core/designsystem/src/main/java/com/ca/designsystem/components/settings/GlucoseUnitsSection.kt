package com.ca.designsystem.components.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ca.designsystem.R
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
        sectionTitle = stringResource(id = R.string.choose_glucose_units)
    ) {
        GlucoseUnitsRadioButtons(
            modifier = Modifier,
            selectedOption = selectedUnits,
            onSelect = { onSelect(it) }
        )
    }
}