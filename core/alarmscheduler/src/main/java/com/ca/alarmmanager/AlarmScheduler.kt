package com.ca.alarmmanager

import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder

interface AlarmScheduler {
    fun scheduleRecordInsulin(reminder: RecordInsulinReminder)
    fun updateInsulinReminder(reminder: RecordInsulinReminder)
    fun scheduleGlucoseMeasuring(reminder: RecordGlucoseReminder)
    fun updateGlucoseReminder(reminder: RecordGlucoseReminder)
    fun cancelRecordInsulinReminder(reminderId: Int)
    fun cancelGlucoseMeasuringReminder(reminderId: Int)
}