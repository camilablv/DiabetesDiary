package com.ca.designsystem.components.glucosemeasuringmark

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
import com.ca.model.MeasuringMark

@Composable
fun MeasuringMarkCards(
    selectedMark: MeasuringMark,
    onSelect: (MeasuringMark) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        measuringMarkIcons.forEach { markIcon ->
            Card(
                modifier = Modifier
                    .clickable { onSelect(markIcon.mark) },
                elevation = Theme.elevations.default,
                backgroundColor = if (markIcon.mark == selectedMark) Theme.colors.secondary else Theme.colors.background
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        modifier = Modifier
                            .size(36.dp),
                        painter = painterResource(id = markIcon.icon),
                        contentDescription = "",
                        tint = if (markIcon.mark == selectedMark) Theme.colors.onSecondary else Theme.colors.onBackground
                    )
                    Text(
                        text = markIcon.mark.text,
                        color = if (markIcon.mark == selectedMark) Theme.colors.onSecondary else Theme.colors.onBackground
                    )
                }
            }
        }
    }
}