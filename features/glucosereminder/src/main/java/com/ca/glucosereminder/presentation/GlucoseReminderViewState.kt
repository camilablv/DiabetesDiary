package com.ca.glucosereminder.presentation

import com.ca.domain.model.RecordGlucoseReminder
import com.ca.domain.model.RecordInsulinReminder
import com.ca.domain.model.ReminderIteration
import java.time.LocalTime

data class GlucoseReminderViewState(
    val editableReminder: RecordGlucoseReminder? = null,
    val time: LocalTime = LocalTime.now(),
    val iteration: ReminderIteration = ReminderIteration.DAILY,
    val isInEditMode: Boolean = false
)