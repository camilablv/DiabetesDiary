package com.ca.reminders.presentation.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.Insulin
import com.ca.model.RecordInsulinReminder

@Composable
fun InsulinRemindersPage(
    reminders: List<Pair<RecordInsulinReminder, Insulin>>
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(reminders.size) {
            InsulinReminderCard(reminder = reminders[it])
        }
        item { 
            Spacer(modifier = Modifier.fillMaxWidth().height(64.dp))
        }
    }
}

@Composable
fun InsulinReminderCard(reminder: Pair<RecordInsulinReminder, Insulin>) {
    Card(
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
                    text = reminder.first.time.toString(),
                    style = Theme.typography.bodyLarge
                )
                Text(
                    text = reminder.first.iteration.text,
                    style = Theme.typography.bodyLarge
                )
            }
            
            Text(
                text = reminder.second.name,
                style = Theme.typography.bodyLarge
            )
            Text(
                text = reminder.first.dose.toString(),
                style = Theme.typography.headlineSmall
            )
            Box(
                modifier = Modifier
                    .background(
                        colorFromHex(reminder.second.color),
                        RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                    )
                    .width(24.dp)
                    .fillMaxHeight()
            )
        }
    }
}