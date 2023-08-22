package com.ca.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject

internal class AlarmSchedulerImpl @Inject constructor(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    private val intent = Intent(context, AlarmReceiver::class.java)

    override fun scheduleRecordInsulin(reminder: RecordInsulinReminder) {
        intent.apply {
            putExtra(AlarmReceiver.INSULIN_ID_KEY, reminder.insulinId)
            putExtra(AlarmReceiver.DOSE_KEY, reminder.dose)
            putExtra(AlarmReceiver.REMINDER_ID_KEY, reminder.id)
        }

        schedule(INSULIN_REMINDER_ID_PREFIX + reminder.id, reminder.time)
    }

    override fun updateInsulinReminder(reminder: RecordInsulinReminder) {
        cancelRecordInsulinReminder(reminder.id)
        if (reminder.enabled) {
            scheduleRecordInsulin(reminder)
        }
    }
    override fun scheduleGlucoseMeasuring(reminder: RecordGlucoseReminder) {
        schedule(GLUCOSE_REMINDER_ID_PREFIX + reminder.id, reminder.time)
    }

    override fun updateGlucoseReminder(reminder: RecordGlucoseReminder) {
        cancelGlucoseMeasuringReminder(reminder.id)
        if (reminder.enabled) {
            scheduleGlucoseMeasuring(reminder)
        }
    }

    private fun schedule(reminderId: String, time: LocalTime) {
        val millis = ZonedDateTime.of(
            LocalDate.now(),
            time,
            ZoneId.of(android.icu.util.TimeZone.getDefault().id)
        ).toInstant().toEpochMilli()

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            millis,
            pendingIntent(reminderId.hashCode())
        )
    }

    override fun cancelRecordInsulinReminder(reminderId: Int) {
        cancel(INSULIN_REMINDER_ID_PREFIX + reminderId)
    }

    override fun cancelGlucoseMeasuringReminder(reminderId: Int) {
        cancel(GLUCOSE_REMINDER_ID_PREFIX + reminderId)
    }

    private fun cancel(reminderId: String) {
        alarmManager.cancel(pendingIntent(reminderId.hashCode()))
    }

    private fun pendingIntent(requestCode: Int): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    companion object {
        private const val INSULIN_REMINDER_ID_PREFIX = "insulin"
        private const val GLUCOSE_REMINDER_ID_PREFIX = "glucose"
    }
}