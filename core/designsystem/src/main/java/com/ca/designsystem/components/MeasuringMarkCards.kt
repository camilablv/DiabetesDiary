package com.ca.designsystem.components

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

internal val measuringMarks = listOf(
    MeasuringMark.GENERAL to com.ca.designsystem.R.drawable.person,
    MeasuringMark.FASTING to com.ca.designsystem.R.drawable.no_meals,
    MeasuringMark.PRE_MEAL to com.ca.designsystem.R.drawable.lunch_dining,
    MeasuringMark.POST_MEAL to com.ca.designsystem.R.drawable.restaurant,
    MeasuringMark.BEFORE_SLEEP to com.ca.designsystem.R.drawable.bedtime,
)

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
        measuringMarks.forEach { mark ->
            Card(
                modifier = Modifier
                    .clickable { onSelect(mark.first) },
                elevation = Theme.elevations.default,
                backgroundColor = if (mark.first == selectedMark) Theme.colors.secondary else Theme.colors.background
            ) {
                Column(
                    modifier = Modifier
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .size(36.dp),
                        painter = painterResource(id = mark.second),
                        contentDescription = "",
                        tint = if (mark.first == selectedMark) Theme.colors.onSecondary else Theme.colors.onBackground
                    )
                    Text(
                        text = mark.first.text,
                        color = if (mark.first == selectedMark) Theme.colors.onSecondary else Theme.colors.onBackground
                    )
                }
            }
        }
    }
}