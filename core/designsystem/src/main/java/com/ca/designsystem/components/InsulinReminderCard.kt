package com.ca.designsystem.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.common.utils.timeOfHHmmPattern
import com.ca.designsystem.theme.Purple100
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.RecordInsulinReminder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InsulinReminderCard(
    modifier: Modifier,
    reminder: RecordInsulinReminder,
    onCheckedChanged: (Boolean) -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {
    val background = if (selected) Purple100 else Theme.colors.background

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
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
        Column(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier

                    .height(64.dp)
                    .fillMaxWidth()
                    ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(2f),
                    verticalArrangement = Arrangement.SpaceBetween
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

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(colorFromHex(reminder.insulin?.color!!), Theme.shapes.large)
                        .aspectRatio(1f)
                )
                Text(
                    modifier = Modifier
                        .weight(7f)
                        .padding(start = 8.dp),
                    text = reminder.insulin?.name!!,
                    style = Theme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier
                        .weight(2f),
                    text = "${reminder.dose} un",
                    style = Theme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }
    }
}