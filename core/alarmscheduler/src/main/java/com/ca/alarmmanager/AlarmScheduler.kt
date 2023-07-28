package com.ca.alarmmanager

import com.ca.model.RecordInsulinReminder
import com.ca.model.ReminderIteration
import java.time.LocalTime

interface AlarmScheduler {
    fun scheduleRecordInsulin(reminder: RecordInsulinReminder)
    fun scheduleGlucoseMeasuring(time: LocalTime, iteration: ReminderIteration)
}