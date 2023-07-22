package com.ca.settings.repository

import com.ca.alarmmanager.ReminderAlarmManager
import com.ca.database.DiaryDataBase
import com.ca.model.RecordInsulinReminder
import com.ca.domain.repository.InsulinReminderRepository
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.time.LocalTime
import javax.inject.Inject

class InsulinReminderRepositoryImpl @Inject constructor(
    private val alarmManager: ReminderAlarmManager,
    private val reminderDataBase: DiaryDataBase
) : InsulinReminderRepository {

    private val reminderDao by lazy { reminderDataBase.insulinReminderDao() }

    override suspend fun addReminder(time: LocalTime, iteration: ReminderIteration, insulin: Insulin, dose: Int) {
        withContext(Dispatchers.IO) {
            reminderDao.insert(
                RecordInsulinReminder(
                    time, iteration, insulin.id, dose
                )
            )
        }
        alarmManager.scheduleOnce(time)
    }

    override suspend fun reminders(): Flow<List<RecordInsulinReminder>> {
        return reminderDao.insulinReminders().flowOn(Dispatchers.IO)
    }
}