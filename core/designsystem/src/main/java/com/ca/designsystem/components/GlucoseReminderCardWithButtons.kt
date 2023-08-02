package com.ca.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Grey100
import com.ca.designsystem.theme.Theme
import com.ca.model.RecordGlucoseReminder

@Composable
fun GlucoseReminderCardWithCheckbox(
    reminder: RecordGlucoseReminder,
    onDoneClick: (RecordGlucoseReminder) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = reminder.time.toString(),
            style = Theme.typography.bodyLarge
        )

        ReminderCard {
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

                TextButton(onClick = { onDoneClick(reminder) }) {
                    Text(text = "Done")
                }
            }
        }
    }
}