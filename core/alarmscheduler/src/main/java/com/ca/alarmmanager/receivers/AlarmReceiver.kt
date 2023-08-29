package com.ca.alarmmanager.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.ca.alarmmanager.AlarmScheduler
import com.ca.domain.model.ListItem
import com.ca.domain.model.RecordGlucoseReminder
import com.ca.domain.model.RecordInsulinReminder
import com.ca.domain.model.ReminderIteration
import com.ca.domain.repository.RemindersRepository
import com.ca.notification.DiaryNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var remindersRepository: RemindersRepository
    @Inject
    lateinit var notificationManager: DiaryNotificationManager
    @Inject
    lateinit var scheduler: AlarmScheduler
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onReceive(context: Context?, intent: Intent) {
        Log.d("EXTRA_MESSAGE", "Received message")

        val reminder = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(REMINDER_KEY)
        } else {
            intent.getParcelableExtra(REMINDER_KEY, ListItem::class.java)
        }

        when (reminder) {
            is RecordInsulinReminder -> {
                notificationManager.showRecordInsulinNotification(reminder)
                handleInsulinReminder(reminder)
            }
            is RecordGlucoseReminder -> {
                notificationManager.postGlucoseMeasuringNotification(reminder)
                handleGlucoseReminder(reminder)
            }
        }
    }

    private fun handleInsulinReminder(recordInsulinReminder: RecordInsulinReminder) {
        if (recordInsulinReminder.iteration == ReminderIteration.DAILY) {
            scheduler.scheduleRecordInsulin(recordInsulinReminder)
        } else {
            scope.launch {
                remindersRepository.updateInsulinReminder(recordInsulinReminder.copy(enabled = false))
            }
        }
    }

    private fun handleGlucoseReminder(recordGlucoseReminder: RecordGlucoseReminder) {
        if (recordGlucoseReminder.iteration == ReminderIteration.DAILY) {
            scheduler.scheduleGlucoseMeasuring(recordGlucoseReminder)
        } else {
            scope.launch {
                remindersRepository.updateGlucoseReminder(recordGlucoseReminder.copy(enabled = false))
            }
        }
    }

    companion object {
        const val REMINDER_KEY = "reminder_key"
    }
}