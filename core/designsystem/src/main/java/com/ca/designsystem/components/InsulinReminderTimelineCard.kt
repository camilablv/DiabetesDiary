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
import com.ca.designsystem.R
import com.ca.designsystem.theme.Purple100
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.RecordInsulinReminder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InsulinReminderTimelineCard(
    reminder: RecordInsulinReminder,
    selected: Boolean,
    onDoneClick: (RecordInsulinReminder) -> Unit,
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
                    text = "Reminder: Taking insulin",
                    style = Theme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .height(56.dp)
                        .padding(start = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledIcon(backgroundColor = colorFromHex(reminder.insulin?.color!!), icon = R.drawable.notifications)

                    Column(
                        modifier = Modifier
                            .padding(4.dp)
                            .weight(2f),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = reminder.insulin?.name!!,
                            style = Theme.typography.bodyLarge
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = reminder.dose.toString(),
                                style = Theme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "IU",
                                style = Theme.typography.bodySmall,
                            )
                        }
                    }

                    TextButton(onClick = { onDoneClick(reminder) }) {
                        Text(text = "Done")
                    }
                }
            }
        }
    }
}