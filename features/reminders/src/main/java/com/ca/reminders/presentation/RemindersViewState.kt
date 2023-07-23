package com.ca.reminders.presentation

import com.ca.model.Insulin
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder

data class RemindersViewState(
    val insulinReminders: List<Pair<RecordInsulinReminder, Insulin>> = listOf(),
    val glucoseReminders: List<RecordGlucoseReminder> = listOf()
)
