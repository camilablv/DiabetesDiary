package com.ca.data.repository

import com.ca.alarmmanager.AlarmScheduler
import com.ca.data.di.IoDispatcher
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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalTime
import javax.inject.Inject

class InsulinReminderRepositoryImpl @Inject constructor(
    private val alarmManager: AlarmScheduler,
    private val insulinReminderDao: InsulinReminderDao,
    private val glucoseReminderDao: GlucoseReminderDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemindersRepository {

    override suspend fun addInsulinReminder(time: LocalTime, iteration: ReminderIteration, insulin: Insulin, dose: Int) {
        val reminder = RecordInsulinReminderEntity(
            time, iteration, insulin.id, dose, true
        )
        withContext(ioDispatcher) {
            insulinReminderDao.insert(reminder)
        }
        alarmManager.scheduleRecordInsulin(reminder.asExternalModel())
    }

    override suspend fun addRecordGlucoseReminder(time: LocalTime, iteration: ReminderIteration) {
        withContext(ioDispatcher) {
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
            .flowOn(ioDispatcher)
    }

    override suspend fun glucoseReminders(): Flow<List<RecordGlucoseReminder>> {
        return glucoseReminderDao
            .glucoseReminders()
            .map { list -> list.map { it.asExternalModel() } }
            .flowOn(ioDispatcher)
    }

    override suspend fun deleteGlucoseReminder(reminder: RecordGlucoseReminder) {
        withContext(ioDispatcher) {
            glucoseReminderDao.delete(reminder.asEntity())
        }
    }

    override suspend fun deleteInsulinReminder(reminder: RecordInsulinReminder) {
        withContext(ioDispatcher) {
            insulinReminderDao.delete(reminder.asEntity())
        }
    }

    override suspend fun updateInsulinReminder(reminder: RecordInsulinReminder) {
        withContext(ioDispatcher) {
            insulinReminderDao.update(reminder.asEntity())
        }
    }

    override suspend fun updateGlucoseReminder(reminder: RecordGlucoseReminder) {
        withContext(ioDispatcher) {
            glucoseReminderDao.update(reminder.asEntity())
        }
    }

    override suspend fun insulinRemindersByInsulinId(insulinId: String): List<RecordInsulinReminder> {
        return withContext(ioDispatcher) {
            insulinReminderDao
                .insulinRemindersByInsulinId(insulinId)
                .map { it.asExternalModel() }
        }
    }

    override suspend fun glucoseReminderById(id: Int): RecordGlucoseReminder {
        return withContext(ioDispatcher) {
            glucoseReminderDao.reminderById(id).asExternalModel()
        }
    }

    override suspend fun insulinReminderById(id: Int): RecordInsulinReminder {
        return withContext(ioDispatcher) {
            insulinReminderDao.insulinReminderById(id).asExternalModel()
        }
    }
}