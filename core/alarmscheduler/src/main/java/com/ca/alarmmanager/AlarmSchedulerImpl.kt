package com.ca.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.ca.alarmmanager.extensions.timeExactForAlarm
import com.ca.model.RecordInsulinReminder
import com.ca.model.ReminderIteration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

internal class AlarmSchedulerImpl @Inject constructor(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    private val intent = Intent(context, AlarmReceiver::class.java)

    private val pendingIntent by lazy {
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    override fun scheduleRecordInsulin(reminder: RecordInsulinReminder) {
        intent.apply {
            putExtra(AlarmReceiver.INSULIN_ID_KEY, reminder.insulinId)
            putExtra(AlarmReceiver.DOSE_KEY, reminder.dose)
            putExtra(AlarmReceiver.REMINDER_ID_KEY, reminder.id)
        }

        scheduleOnce(reminder.time)
    }

    override fun scheduleGlucoseMeasuring(time: LocalTime, iteration: ReminderIteration) {

    }

    private fun scheduleOnce(time: LocalTime) {
        alarmManager.setExact(
            AlarmManager.ELAPSED_REALTIME,
            SystemClock.elapsedRealtime() + 5 * 1000,
            pendingIntent
        )
    }

    private fun scheduleDaily(time: LocalTime) {
        val timeInMillis = Calendar.Builder()
            .setTimeOfDay(time.hour, time.minute, time.second)
            .setTimeZone(TimeZone.getDefault())
            .build()
            .timeInMillis

        val dateTime = LocalDateTime.of(LocalDate.now(), time).atZone(ZoneId.systemDefault()).toEpochSecond() * 1000

        val timeExact = Calendar.getInstance().timeExactForAlarm(time).timeInMillis

        val realTime = SystemClock.elapsedRealtime() + 3 * 1000

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            timeExact,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}