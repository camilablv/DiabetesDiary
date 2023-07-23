package com.ca.settings.repository

import com.ca.alarmmanager.ReminderAlarmManager
import com.ca.database.DiaryDatabase
import com.ca.model.RecordInsulinReminder
import com.ca.domain.repository.InsulinReminderRepository
import com.ca.model.Insulin
import com.ca.model.RecordGlucoseReminder
import com.ca.model.ReminderIteration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.time.LocalTime
import javax.inject.Inject

class InsulinReminderRepositoryImpl @Inject constructor(
    private val alarmManager: ReminderAlarmManager,
    private val reminderDataBase: DiaryDatabase
) : InsulinReminderRepository {

    private val insulinReminderDao by lazy { reminderDataBase.insulinReminderDao() }
    private val glucoseReminderDao by lazy { reminderDataBase.glucoseReminderDao() }

    override suspend fun addInsulinReminder(time: LocalTime, iteration: ReminderIteration, insulin: Insulin, dose: Int) {
        withContext(Dispatchers.IO) {
            insulinReminderDao.insert(
                RecordInsulinReminder(
                    time, iteration, insulin.id, dose
                )
            )
        }
        alarmManager.scheduleOnce(time)
    }

    override suspend fun addRecordGlucoseReminder(time: LocalTime, iteration: ReminderIteration) {
        withContext(Dispatchers.IO) {
            glucoseReminderDao.insert(
                RecordGlucoseReminder(time, iteration)
            )
        }
        alarmManager.scheduleOnce(time)
    }

    override suspend fun insulinReminders(): Flow<List<RecordInsulinReminder>> {
        return insulinReminderDao.insulinReminders().flowOn(Dispatchers.IO)
    }

    override suspend fun glucoseReminders(): Flow<List<RecordGlucoseReminder>> {
        return glucoseReminderDao.glucoseReminders().flowOn(Dispatchers.IO)
    }

    override suspend fun deleteGlucoseReminder(reminder: RecordGlucoseReminder) {
        withContext(Dispatchers.IO) {
            glucoseReminderDao.delete(reminder)
        }
    }

    override suspend fun deleteInsulinReminder(reminder: RecordInsulinReminder) {
        withContext(Dispatchers.IO) {
            insulinReminderDao.delete(reminder)
        }
    }
}