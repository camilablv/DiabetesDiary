package com.ca.reminders.data.repository

import com.ca.alarmmanager.ReminderAlarmManager
import com.ca.reminders.domain.repository.RemindersRepository
import java.time.LocalTime
import javax.inject.Inject

class RemindersRepositoryImpl @Inject constructor(
    private val alarmManager: ReminderAlarmManager
) : RemindersRepository {
    override fun scheduleOnce(time: LocalTime) {
        alarmManager.scheduleOnce(time)
    }
}