package com.ca.insulinreminder.domain

import com.ca.database.entities.RecordInsulinReminder
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import java.time.LocalTime

interface InsulinReminderRepository {
    fun reminders(): List<RecordInsulinReminder>
    suspend fun insulins(): List<Insulin>
    suspend fun addReminder(
        time: LocalTime,
        iteration: ReminderIteration,
        insulin: Insulin,
        dose: Int
    )
}