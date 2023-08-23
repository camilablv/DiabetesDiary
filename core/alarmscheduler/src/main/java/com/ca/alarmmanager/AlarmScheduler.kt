package com.ca.alarmmanager

import com.ca.domain.model.RecordGlucoseReminder
import com.ca.domain.model.RecordInsulinReminder

interface AlarmScheduler {
    fun scheduleRecordInsulin(reminder: RecordInsulinReminder)
    fun updateInsulinReminder(reminder: RecordInsulinReminder)
    fun scheduleGlucoseMeasuring(reminder: RecordGlucoseReminder)
    fun updateGlucoseReminder(reminder: RecordGlucoseReminder)
    fun cancelRecordInsulinReminder(reminderId: Int)
    fun cancelGlucoseMeasuringReminder(reminderId: Int)
}