package com.ca.domain.repository

import com.ca.model.RecordInsulinReminder
import com.ca.model.Insulin
import com.ca.model.RecordGlucoseReminder
import com.ca.model.ReminderIteration
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

interface InsulinReminderRepository {
    suspend fun insulinReminders(): Flow<List<RecordInsulinReminder>>
    suspend fun glucoseReminders(): Flow<List<RecordGlucoseReminder>>
    suspend fun addInsulinReminder(
        time: LocalTime,
        iteration: ReminderIteration,
        insulin: Insulin,
        dose: Int
    )
    suspend fun addRecordGlucoseReminder(
        time: LocalTime,
        iteration: ReminderIteration
    )
    suspend fun deleteGlucoseReminder(reminder: RecordGlucoseReminder)
    suspend fun deleteInsulinReminder(reminder: RecordInsulinReminder)
}