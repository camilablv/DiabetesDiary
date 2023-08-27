package com.ca.glucosereminder.presentation

import com.ca.domain.model.RecordInsulinReminder
import com.ca.domain.model.ReminderIteration
import java.time.LocalTime

data class GlucoseReminderViewState(
    val editableReminder: RecordInsulinReminder? = null,
    val time: LocalTime = LocalTime.now(),
    val iteration: ReminderIteration = ReminderIteration.ONCE,
    val isInEditMode: Boolean = false
)