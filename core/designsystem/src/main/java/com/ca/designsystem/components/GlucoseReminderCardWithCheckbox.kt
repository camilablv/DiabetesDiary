package com.ca.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.model.RecordGlucoseReminder
import kotlin.random.Random

@Composable
fun GlucoseReminderCardWithCheckbox(
    reminder: RecordGlucoseReminder
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
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(2f),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(48.dp),
                        painter = painterResource(id = com.ca.designsystem.R.drawable.blood_filled),
                        contentDescription = ""
                    )

                    Text(
                        text = "Glucose measuring",
                        style = Theme.typography.bodyLarge
                    )
                }

                Checkbox(
                    checked = Random.nextBoolean(),
                    onCheckedChange = {}
                )
            }
        }
    }
}