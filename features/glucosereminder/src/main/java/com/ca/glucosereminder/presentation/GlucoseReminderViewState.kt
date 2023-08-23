package com.ca.glucosereminder.presentation

import com.ca.domain.model.ReminderIteration
import java.time.LocalTime

data class GlucoseReminderViewState(
    val time: LocalTime = LocalTime.now(),
    val iteration: ReminderIteration = ReminderIteration.ONCE,
)