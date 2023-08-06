package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.Insulin
import com.ca.model.RecordInsulinReminder

@Composable
fun InsulinReminderCard(
    modifier: Modifier,
    reminder: RecordInsulinReminder
) {
    Card(
        modifier = modifier,
        shape = Theme.shapes.large,
        elevation = Theme.elevations.default
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        colorFromHex(reminder.insulin?.color!!),
                        RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                    )
                    .width(24.dp)
                    .fillMaxHeight()
            )
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .weight(2f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = reminder.time.toString(),
                    style = Theme.typography.bodyLarge
                )
                Text(
                    text = reminder.iteration.text,
                    style = Theme.typography.bodyLarge
                )
            }

            Text(
                modifier = Modifier
                    .weight(7f),
                text = reminder.insulin?.name!!,
                style = Theme.typography.bodyLarge
            )
            Text(
                modifier = Modifier
                    .weight(1f),
                text = reminder.dose.toString(),
                style = Theme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}