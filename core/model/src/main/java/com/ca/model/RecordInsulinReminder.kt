package com.ca.model

import java.time.LocalTime


data class RecordInsulinReminder(
    override val id: Int,
    override val time: LocalTime,
    override val iteration: ReminderIteration,
    val insulinId: String,
    val dose: Int,
    val insulin: Insulin? = null
): Reminder