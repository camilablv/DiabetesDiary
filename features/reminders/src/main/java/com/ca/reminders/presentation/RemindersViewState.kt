package com.ca.reminders.presentation

import com.ca.domain.model.RecordGlucoseReminder
import com.ca.domain.model.RecordInsulinReminder

data class RemindersViewState(
    val insulinReminders: List<RecordInsulinReminder> = listOf(),
    val glucoseReminders: List<RecordGlucoseReminder> = listOf()
)
