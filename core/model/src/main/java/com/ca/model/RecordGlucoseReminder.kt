package com.ca.model

import java.time.LocalTime

data class RecordGlucoseReminder(
    override val id: Int = 0,
    override val time: LocalTime,
    override val iteration: ReminderIteration
): Reminder
