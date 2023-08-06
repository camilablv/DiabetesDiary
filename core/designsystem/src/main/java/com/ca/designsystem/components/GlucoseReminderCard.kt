package com.ca.designsystem.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.model.RecordGlucoseReminder

@Composable
fun GlucoseReminderCard(
    modifier: Modifier,
    reminder: RecordGlucoseReminder
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp),
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
        }
    }
}