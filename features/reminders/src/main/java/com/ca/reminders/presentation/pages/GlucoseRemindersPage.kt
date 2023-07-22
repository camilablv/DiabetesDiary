package com.ca.reminders.presentation.pages

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.model.RecordGlucoseReminder

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun GlucoseRemindersPage(
    reminders: List<RecordGlucoseReminder>,
    delete: (RecordGlucoseReminder) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = reminders.size,
            key = { reminders[it].id }
        ) {
            val dismissState = rememberDismissState()

            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                delete(reminders[it])
            }

            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier,
                directions = setOf(DismissDirection.EndToStart),
                dismissThresholds = { FractionalThreshold(0.3f)},
                background = {}
            ) {
                GlucoseReminderCard(
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
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(64.dp))
        }
    }
}

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