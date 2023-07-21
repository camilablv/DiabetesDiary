package com.ca.insulinreminder.data

import com.ca.alarmmanager.ReminderAlarmManager
import com.ca.database.db.ReminderDataBase
import com.ca.database.entities.RecordInsulinReminder
import com.ca.datastore.SettingsDataStore
import com.ca.insulinreminder.domain.InsulinReminderRepository
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalTime
import javax.inject.Inject

class InsulinReminderRepositoryImpl @Inject constructor(
    private val alarmManager: ReminderAlarmManager,
    private val reminderDataBase: ReminderDataBase,
    private val dataStore: SettingsDataStore
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

    override fun reminders(): List<RecordInsulinReminder> = reminderDao.insulinReminders()

    override suspend fun insulins(): List<Insulin> {
        return dataStore.insulins()
    }
}