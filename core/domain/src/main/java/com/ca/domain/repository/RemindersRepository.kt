package com.ca.domain.repository

import com.ca.model.Insulin
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder
import com.ca.model.ReminderIteration
import kotlinx.coroutines.flow.Flow
import java.time.LocalTime

interface RemindersRepository {
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
    suspend fun updateInsulinReminder(reminder: RecordInsulinReminder)
    suspend fun updateGlucoseReminder(reminder: RecordGlucoseReminder)
    suspend fun insulinRemindersByInsulinId(insulinId: String): List<RecordInsulinReminder>
    suspend fun glucoseReminderById(id: Int): RecordGlucoseReminder
    suspend fun insulinReminderById(id: Int): RecordInsulinReminder
    suspend fun rescheduleAll()
}