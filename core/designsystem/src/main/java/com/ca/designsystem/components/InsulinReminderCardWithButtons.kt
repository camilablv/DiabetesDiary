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
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme
import com.ca.model.RecordInsulinReminder

@Composable
fun InsulinReminderCardWithCheckbox(
    reminder: RecordInsulinReminder,
    onDoneClick: (RecordInsulinReminder) -> Unit
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

        Card(
            modifier = Modifier,
            shape = Theme.shapes.large,
            elevation = Theme.elevations.default
        ) {
            Row(
                modifier = Modifier
                    .height(56.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(4.dp),
                    painter = painterResource(id = R.drawable.injection),
                    contentDescription = ""
                )

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
                    Text(
                        text = reminder.dose.toString(),
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