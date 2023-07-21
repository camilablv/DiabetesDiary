package com.ca.insulinreminder.data

import com.ca.alarmmanager.ReminderAlarmManager
import com.ca.insulinreminder.domain.InsulinReminderRepository
import java.time.LocalTime
import javax.inject.Inject

class InsulinReminderRepositoryImpl @Inject constructor(
    private val alarmManager: ReminderAlarmManager
) : InsulinReminderRepository {
    override fun scheduleOnce(time: LocalTime) {
        alarmManager.scheduleOnce(time)
    }
}