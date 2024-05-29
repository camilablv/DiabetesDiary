package com.ca.data.repository

import com.ca.alarmmanager.AlarmScheduler
import com.ca.data.di.IoDispatcher
import com.ca.database.dao.GlucoseReminderDao
import com.ca.database.dao.InsulinReminderDao
import com.ca.database.model.RecordGlucoseReminderEntity
import com.ca.database.model.RecordInsulinReminderEntity
import com.ca.database.model.asEntity
import com.ca.database.model.asExternalModel
import com.ca.model.Insulin
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder
import com.ca.model.ReminderIteration
import com.ca.domain.repository.RemindersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalTime
import javax.inject.Inject

class RemindersRepositoryImpl @Inject constructor(
    private val alarmManager: AlarmScheduler,
    private val insulinReminderDao: InsulinReminderDao,
    private val glucoseReminderDao: GlucoseReminderDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RemindersRepository {

    override suspend fun addInsulinReminder(
        time: LocalTime,
        iteration: ReminderIteration,
        insulin: Insulin,
        dose: Int
    ) {
        withContext(ioDispatcher) {
            val reminderId = insulinReminderDao.insert(
                RecordInsulinReminderEntity(
                    time, iteration, insulin.id, dose, true
                )
            )
            alarmManager.scheduleRecordInsulin(insulinReminderDao.insulinReminderById(reminderId.toInt()).asExternalModel())
        }
    }



    override suspend fun addRecordGlucoseReminder(time: LocalTime, iteration: ReminderIteration) {
        withContext(ioDispatcher) {
            val reminderId = glucoseReminderDao.insert(
                RecordGlucoseReminderEntity(time, iteration, true)
            )
            alarmManager.scheduleGlucoseMeasuring(glucoseReminderDao.reminderById(reminderId.toInt()).asExternalModel())
        }
    }

    override suspend fun insulinReminders(): Flow<List<RecordInsulinReminder>> {
        return insulinReminderDao
            .insulinReminders()
            .map { list -> list.map { it.asExternalModel() } }
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
            alarmManager.cancelGlucoseMeasuringReminder(reminder.id)
        }
    }

    override suspend fun deleteInsulinReminder(reminder: RecordInsulinReminder) {
        withContext(ioDispatcher) {
            insulinReminderDao.delete(reminder.asEntity())
            alarmManager.cancelRecordInsulinReminder(reminder.id)
        }
    }

    override suspend fun updateInsulinReminder(reminder: RecordInsulinReminder) {
        withContext(ioDispatcher) {
            insulinReminderDao.update(reminder.asEntity())
            alarmManager.updateInsulinReminder(reminder)
        }
    }

    override suspend fun updateGlucoseReminder(reminder: RecordGlucoseReminder) {
        withContext(ioDispatcher) {
            glucoseReminderDao.update(reminder.asEntity())
            alarmManager.updateGlucoseReminder(reminder)
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

    override suspend fun rescheduleAll() {
        rescheduleInsulinReminders()
        rescheduleGlucoseReminders()
    }

    private suspend fun rescheduleInsulinReminders() {
        insulinReminders().first().forEach {
            if (it.enabled) alarmManager.scheduleRecordInsulin(it)
        }
    }

    private suspend fun rescheduleGlucoseReminders() {
        glucoseReminders().first().forEach {
            if (it.enabled) alarmManager.scheduleGlucoseMeasuring(it)
        }
    }
}