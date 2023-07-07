package com.ca.recordglucose.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.recordglucose.domain.model.MeasuringMark
import com.ca.recordglucose.domain.model.measuringMarks

@Composable
fun GlucoseStatusCards(
    selectedMark: MeasuringMark,
    onSelect: (MeasuringMark) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        measuringMarks.forEach { status ->
            Card(
                modifier = Modifier
                    .clickable { onSelect(status) },
                elevation = Theme.elevations.default,
                backgroundColor = if (status == selectedMark) Theme.colors.secondary else Theme.colors.background
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .size(36.dp),
                        painter = painterResource(id = status.icon),
                        contentDescription = "",
                        tint = if (status == selectedMark) Theme.colors.onSecondary else Theme.colors.onBackground
                    )
                    Text(
                        text = status.time,
                        color = if (status == selectedMark) Theme.colors.onSecondary else Theme.colors.onBackground
                    )
                }
            }
        }
    }
}