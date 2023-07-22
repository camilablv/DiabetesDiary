package com.ca.reminders.presentation.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.model.RecordInsulinReminder

@Composable
fun InsulinRemindersPage(
    reminders: List<RecordInsulinReminder>
) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(reminders.size) {
            InsulinReminderCard(reminder = reminders[it])
        }
    }
}

@Composable
fun InsulinReminderCard(reminder: RecordInsulinReminder) {
    Card {
        Text(text = reminder.time.toString())
    }
}