package com.ca.data.repository

import com.ca.alarmmanager.AlarmScheduler
import com.ca.database.DiaryDatabase
import com.ca.database.dao.GlucoseReminderDao
import com.ca.database.dao.InsulinReminderDao
import com.ca.database.model.RecordGlucoseReminderEntity
import com.ca.database.model.RecordInsulinReminderEntity
import com.ca.database.model.asEntity
import com.ca.database.model.asExternalModel
import com.ca.domain.repository.InsulinReminderRepository
import com.ca.model.Insulin
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder
import com.ca.model.ReminderIteration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import java.time.LocalTime
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class InsulinReminderRepositoryImpl @Inject constructor(
    private val alarmManager: AlarmScheduler,
    private val insulinReminderDao: InsulinReminderDao,
    private val glucoseReminderDao: GlucoseReminderDao
) : InsulinReminderRepository {

    override suspend fun addInsulinReminder(time: LocalTime, iteration: ReminderIteration, insulin: Insulin, dose: Int) {
        val reminder = RecordInsulinReminderEntity(
            time, iteration, insulin.id, dose
        )
        withContext(Dispatchers.IO) {
            insulinReminderDao.insert(reminder)
        }
        alarmManager.scheduleRecordInsulin(reminder.asExternalModel())
    }

    override suspend fun addRecordGlucoseReminder(time: LocalTime, iteration: ReminderIteration) {
        withContext(Dispatchers.IO) {
            glucoseReminderDao.insert(
                RecordGlucoseReminderEntity(time, iteration)
            )
        }
        alarmManager.scheduleGlucoseMeasuring(time, iteration)
    }

    override suspend fun insulinReminders(): Flow<List<RecordInsulinReminder>> {
        return insulinReminderDao
            .insulinReminders()
            .flowOn(Dispatchers.IO)
            .mapLatest { list ->
                list.map { it.asExternalModel() }
            }
    }


    override suspend fun glucoseReminders(): Flow<List<RecordGlucoseReminder>> {
        return glucoseReminderDao
            .glucoseReminders()
            .flowOn(Dispatchers.IO)
            .mapLatest { list ->
                list.map { it.asExternalModel() }
            }
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
}