package com.ca.model

import java.time.LocalTime

data class RecordGlucoseReminder(
    val id: Int = 0,
    val time: LocalTime,
    val iteration: ReminderIteration
)
