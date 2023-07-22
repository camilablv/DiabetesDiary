package com.ca.reminders.presentation.pages

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.Insulin
import com.ca.model.RecordInsulinReminder

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun InsulinRemindersPage(
    reminders: List<Pair<RecordInsulinReminder, Insulin>>,
    delete: (RecordInsulinReminder) -> Unit
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            reminders.size,
            key = { reminders[it].first.id }
        ) {
            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                delete(reminders[it].first)
            }

            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier,
                directions = setOf(DismissDirection.EndToStart),
                dismissThresholds = { FractionalThreshold(0.3f) },
                background = {}
            ) {
                InsulinReminderCard(
                    modifier = Modifier
                        .animateItemPlacement(
                            animationSpec = tween(
                                durationMillis = 2000,
                                delayMillis = 500,
                                easing = LinearOutSlowInEasing
                            )
                        ),
                    reminder = reminders[it]
                )
            }
        }
        item { 
            Spacer(modifier = Modifier.fillMaxWidth().height(64.dp))
        }
    }
}

@Composable
fun InsulinReminderCard(
    modifier: Modifier,
    reminder: Pair<RecordInsulinReminder, Insulin>
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
                        colorFromHex(reminder.second.color),
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
                    text = reminder.first.time.toString(),
                    style = Theme.typography.bodyLarge
                )
                Text(
                    text = reminder.first.iteration.text,
                    style = Theme.typography.bodyLarge
                )
            }
            
            Text(
                modifier = Modifier
                    .weight(7f),
                text = reminder.second.name,
                style = Theme.typography.bodyLarge
            )
            Text(
                modifier = Modifier
                    .weight(1f),
                text = reminder.first.dose.toString(),
                style = Theme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}