package com.ca.reminders.presentation.pages

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.InsulinReminderCard
import com.ca.model.RecordInsulinReminder

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun InsulinRemindersPage(
    reminders: List<RecordInsulinReminder>,
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