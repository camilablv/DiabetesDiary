package com.ca.glucosereminder.presentation

import com.ca.model.RecordGlucoseReminder
import com.ca.model.ReminderIteration
import java.time.LocalTime

data class GlucoseReminderViewState(
    val editableReminder: RecordGlucoseReminder? = null,
    val time: LocalTime = LocalTime.now(),
    val iteration: ReminderIteration = ReminderIteration.DAILY,
    val isInEditMode: Boolean = false
)