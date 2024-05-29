package com.ca.alarmmanager.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import android.util.Log
import com.ca.alarmmanager.AlarmScheduler
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder
import com.ca.model.ReminderIteration
import com.ca.domain.repository.RemindersRepository
import com.ca.notification.DiaryNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
sealed class ReminderId : Parcelable {
    data class InsulinReminderId(val id: Int) : ReminderId()
    data class GlucoseReminderId(val id: Int) : ReminderId()
}

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

        val reminderId = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(REMINDER_KEY)
        } else {
            intent.getParcelableExtra(REMINDER_KEY, ReminderId::class.java)
        }

        when (reminderId) {
            is ReminderId.InsulinReminderId -> {
                scope.launch {
                    val reminder = remindersRepository.insulinReminderById(reminderId.id)
                    notificationManager.showRecordInsulinNotification(reminder)
                    handleInsulinReminder(reminder)
                }
            }
            is ReminderId.GlucoseReminderId -> {
                scope.launch {
                    val reminder = remindersRepository.glucoseReminderById(reminderId.id)
                    notificationManager.postGlucoseMeasuringNotification(reminder)
                    handleGlucoseReminder(reminder)
                }
            }
            else -> {}
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