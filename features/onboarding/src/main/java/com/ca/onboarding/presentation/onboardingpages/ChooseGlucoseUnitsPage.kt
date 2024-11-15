package com.ca.onboarding.presentation.onboardingpages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme
import com.ca.model.GlucoseUnits

@Composable
fun ChooseGlucoseUnitsPage(
    defaultUnit: String,
    select: (GlucoseUnits) -> Unit
) {
    var selectedUnit = defaultUnit

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.choose_glucose_units),
            style = Theme.typography.headlineLarge
        )

        GlucoseUnits.units.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        selectedUnit = it.unit
                        select(it)
                    }
            ) {
                RadioButton(
                    selected = selectedUnit == it.unit,
                    onClick = {
                        selectedUnit = it.unit
                        select(it)
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
}