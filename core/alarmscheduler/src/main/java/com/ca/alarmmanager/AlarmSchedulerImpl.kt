package com.ca.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import com.ca.alarmmanager.extensions.timeExactForAlarm
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
    private val pendingIntent by lazy {
        val intent = Intent(context, AlarmReceiver::class.java).also {
            it.putExtra("EXTRA_MESSAGE", "Received message!")
        }
        PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    override fun scheduleOnce(time: LocalTime) {
        val timeInMillis = Calendar.Builder()
            .setTimeOfDay(time.hour, time.minute, time.second)
            .setTimeZone(TimeZone.getDefault())
            .setDate(2023, 7, 20)
            .build()
            .timeInMillis

        val dateTime = LocalDateTime.of(LocalDate.now(), time).atZone(ZoneId.systemDefault()).toEpochSecond() * 1000

        alarmManager.setExact(
            AlarmManager.ELAPSED_REALTIME,
            SystemClock.elapsedRealtime() + 60 * 1000,
            pendingIntent
        )
    }

    override fun scheduleDaily(time: LocalTime) {
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

    override fun cancel() {
        TODO("Not yet implemented")
    }
}