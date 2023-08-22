package com.ca.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.ca.model.RecordInsulinReminder
import com.ca.model.ReminderIteration
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

        schedule("insulin${reminder.id}", reminder.time)
    }

    override fun scheduleGlucoseMeasuring(time: LocalTime, iteration: ReminderIteration) {}

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

    private fun pendingIntent(requestCode: Int): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}