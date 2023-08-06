package com.ca.designsystem.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Grey100
import com.ca.designsystem.theme.Purple100
import com.ca.designsystem.theme.Theme
import com.ca.model.RecordGlucoseReminder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GlucoseReminderTimelineCard(
    reminder: RecordGlucoseReminder,
    selected: Boolean,
    onAddClick: (RecordGlucoseReminder) -> Unit,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val backgroundColor = if (selected) Purple100 else Theme.colors.background

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = reminder.time.toString(),
            style = Theme.typography.bodyLarge
        )

        ReminderCard(
            backgroundColor = backgroundColor
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = "Reminder: Measuring glucose level",
                    style = Theme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .height(56.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(2f),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FilledIcon(backgroundColor = Grey100, icon = com.ca.designsystem.R.drawable.blood_filled)

                        Text(
                            text = "Glucose measuring",
                            style = Theme.typography.bodyLarge
                        )
                    }

                    TextButton(onClick = {
                        onAddClick(reminder)
                    }
                    ) {
                        Text(text = "Add")
                    }
                }
            }
        }
    }
}