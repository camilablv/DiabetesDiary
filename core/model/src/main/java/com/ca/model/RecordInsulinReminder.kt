package com.ca.model

import java.time.LocalTime


data class RecordInsulinReminder(
    val id: Int,
    val time: LocalTime,
    val iteration: ReminderIteration,
    val insulinId: String,
    val dose: Int
)