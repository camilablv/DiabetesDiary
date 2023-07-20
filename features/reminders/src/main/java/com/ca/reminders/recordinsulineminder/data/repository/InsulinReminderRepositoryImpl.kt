package com.ca.reminders.recordinsulineminder.data.repository

import com.ca.alarmmanager.ReminderAlarmManager
import com.ca.reminders.recordinsulineminder.domain.repository.InsulinReminderRepository
import java.time.LocalTime
import javax.inject.Inject

class InsulinReminderRepositoryImpl @Inject constructor(
    private val alarmManager: ReminderAlarmManager
) : InsulinReminderRepository {
    override fun schedule(time: LocalTime) {
        alarmManager.scheduleOnce(time)
    }
}