package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.model.GlucoseUnits

@Composable
fun GlucoseUnitsRadioButtons(
    modifier: Modifier,
    units:  List<GlucoseUnits>,
    defaultUnit: GlucoseUnits,
    onSelect: (GlucoseUnits) -> Unit
) {
    var selectedUnit = defaultUnit

    units.forEach {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    selectedUnit = it
                    onSelect(it)
                }
        ) {
            RadioButton(
                selected = selectedUnit == it,
                onClick = {
                    selectedUnit = it
                    onSelect(it)
                },
                enabled = true,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Theme.colors.primary,
                    unselectedColor = Color.Gray
                ),
                modifier = Modifier
                    .size(30.dp)
                    .testTag(it.unit)
            )

            Text(
                text = it.unit,
                modifier = Modifier
                    .padding(start = 25.dp)
                    .align(Alignment.CenterVertically),
                style = Theme.typography.bodyLarge,
                color = Theme.colors.onBackground,
            )
        }
    }
}