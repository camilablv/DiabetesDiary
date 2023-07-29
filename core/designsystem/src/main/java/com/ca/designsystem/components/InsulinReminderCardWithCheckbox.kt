package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.RecordInsulinReminder

@Composable
fun InsulinReminderCardWithCheckbox(
    reminder: RecordInsulinReminder
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
                horizontalArrangement = Arrangement.SpaceBetween
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
                        text = reminder.insulin?.name!!,
                        style = Theme.typography.bodyLarge
                    )
                    Text(
                        text = reminder.dose.toString(),
                        style = Theme.typography.bodyLarge
                    )
                }
                
                Checkbox(checked = false, onCheckedChange = {})
            }
            
        }
    }
}