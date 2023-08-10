package com.ca.data.repository

import com.ca.alarmmanager.AlarmScheduler
import com.ca.database.dao.GlucoseReminderDao
import com.ca.database.dao.InsulinReminderDao
import com.ca.database.model.RecordGlucoseReminderEntity
import com.ca.database.model.RecordInsulinReminderEntity
import com.ca.database.model.asEntity
import com.ca.database.model.asExternalModel
import com.ca.domain.repository.RemindersRepository
import com.ca.model.Insulin
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder
import com.ca.model.ReminderIteration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalTime
import javax.inject.Inject

class InsulinReminderRepositoryImpl @Inject constructor(
    private val alarmManager: AlarmScheduler,
    private val insulinReminderDao: InsulinReminderDao,
    private val glucoseReminderDao: GlucoseReminderDao
) : RemindersRepository {

    override suspend fun addInsulinReminder(time: LocalTime, iteration: ReminderIteration, insulin: Insulin, dose: Int) {
        val reminder = RecordInsulinReminderEntity(
            time, iteration, insulin.id, dose, true
        )
        withContext(Dispatchers.IO) {
            insulinReminderDao.insert(reminder)
        }
        alarmManager.scheduleRecordInsulin(reminder.asExternalModel())
    }

    override suspend fun addRecordGlucoseReminder(time: LocalTime, iteration: ReminderIteration) {
        withContext(Dispatchers.IO) {
            glucoseReminderDao.insert(
                RecordGlucoseReminderEntity(time, iteration, true)
            )
        }
        alarmManager.scheduleGlucoseMeasuring(time, iteration)
    }

    override suspend fun insulinReminders(): Flow<List<RecordInsulinReminder>> {
        return insulinReminderDao
            .insulinReminders()
            .map { list -> list.map { it.asExternalModel() }
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun glucoseReminders(): Flow<List<RecordGlucoseReminder>> {
        return glucoseReminderDao
            .glucoseReminders()
            .map { list -> list.map { it.asExternalModel() } }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun deleteGlucoseReminder(reminder: RecordGlucoseReminder) {
        withContext(Dispatchers.IO) {
            glucoseReminderDao.delete(reminder.asEntity())
        }
    }

    override suspend fun deleteInsulinReminder(reminder: RecordInsulinReminder) {
        withContext(Dispatchers.IO) {
            insulinReminderDao.delete(reminder.asEntity())
        }
    }

    override suspend fun updateInsulinReminder(reminder: RecordInsulinReminder) {
        withContext(Dispatchers.IO) {
            insulinReminderDao.update(reminder.asEntity())
        }
    }

    override suspend fun updateGlucoseReminder(reminder: RecordGlucoseReminder) {
        withContext(Dispatchers.IO) {
            glucoseReminderDao.update(reminder.asEntity())
        }
    }

    override suspend fun insulinRemindersByInsulinId(insulinId: String): List<RecordInsulinReminder> {
        return withContext(Dispatchers.IO) {
            insulinReminderDao
                .insulinRemindersByInsulinId(insulinId)
                .map { it.asExternalModel() }
        }
    }
}