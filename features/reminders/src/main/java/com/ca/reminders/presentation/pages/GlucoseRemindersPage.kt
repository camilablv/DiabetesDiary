package com.ca.reminders.presentation.pages

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.GlucoseReminderCard
import com.ca.domain.model.RecordGlucoseReminder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GlucoseRemindersPage(
    reminders: List<RecordGlucoseReminder>,
    onEnabledChange: (RecordGlucoseReminder, Boolean) -> Unit,
    onClick: (RecordGlucoseReminder) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = reminders.size
        ) {
            val reminder = reminders[it]
            GlucoseReminderCard(
                modifier = Modifier
                    .animateItemPlacement(
                        animationSpec = tween(
                            durationMillis = 2000,
                            delayMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    ),
                reminder = reminder,
                onCheckedChanged = { enabled -> onEnabledChange(reminder, enabled) },
                onClick = { onClick(reminder) }
            )
        }
        item {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(64.dp))
        }
    }
}

