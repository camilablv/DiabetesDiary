package com.ca.model

import java.time.LocalTime


data class RecordInsulinReminder(
    val id: Int,
    override val time: LocalTime,
    val iteration: ReminderIteration,
    val insulinId: String,
    val dose: Int,
    val insulin: Insulin? = null
): ListItem