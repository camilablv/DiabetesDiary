package com.ca.designsystem.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ca.common.utils.timeOfHHmmPattern
import com.ca.designsystem.theme.Purple100
import com.ca.designsystem.theme.Theme
import com.ca.model.RecordGlucoseReminder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GlucoseReminderCard(
    modifier: Modifier,
    reminder: RecordGlucoseReminder,
    onCheckedChanged: (Boolean) -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val background = if (selected) Purple100 else Theme.colors.background

    Card(
        modifier = modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick,
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
        shape = Theme.shapes.large,
        elevation = Theme.elevations.default,
        backgroundColor = background
    ) {
        Row(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.SpaceBetween,
                
            ) {
                Text(
                    text = reminder.time.timeOfHHmmPattern(),
                    style = Theme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = reminder.iteration.text,
                    style = Theme.typography.bodyLarge
                )
            }

            Switch(
                checked = reminder.enabled,
                onCheckedChange = { onCheckedChanged(it) },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = Theme.colors.secondary,
                )
            )
        }
    }
}