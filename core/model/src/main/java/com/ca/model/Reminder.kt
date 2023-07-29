package com.ca.model

import java.time.LocalTime

interface Reminder {
    val id: Int
    val time: LocalTime
    val iteration: ReminderIteration
}