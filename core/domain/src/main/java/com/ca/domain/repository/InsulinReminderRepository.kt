package com.ca.domain.repository

import com.ca.model.RecordInsulinReminder
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

interface InsulinReminderRepository {
    suspend fun reminders(): Flow<List<RecordInsulinReminder>>
    suspend fun addReminder(
        time: LocalTime,
        iteration: ReminderIteration,
        insulin: Insulin,
        dose: Int
    )
}