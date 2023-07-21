package com.ca.domain.repository

import com.ca.database.entities.RecordInsulinReminder
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import java.time.LocalTime

interface InsulinReminderRepository {
    fun reminders(): List<RecordInsulinReminder>
    suspend fun addReminder(
        time: LocalTime,
        iteration: ReminderIteration,
        insulin: Insulin,
        dose: Int
    )
}