package com.ca.reminders.presentation

import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder

data class RemindersViewState(
    val insulinReminders: List<RecordInsulinReminder> = listOf(),
    val glucoseReminders: List<RecordGlucoseReminder> = listOf()
)
