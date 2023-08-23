package com.ca.alarmmanager.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.ca.domain.model.ListItem
import com.ca.domain.model.RecordGlucoseReminder
import com.ca.domain.model.RecordInsulinReminder
import com.ca.notification.DiaryNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject lateinit var notificationManager: DiaryNotificationManager

    override fun onReceive(context: Context?, intent: Intent) {
        Log.d("EXTRA_MESSAGE", "Received message")

        val reminder = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(REMINDER_KEY)
        } else {
            intent.getParcelableExtra(REMINDER_KEY, ListItem::class.java)
        }

        when(reminder) {
            is RecordInsulinReminder -> {
                notificationManager.showRecordInsulinNotification(reminder.insulinId, reminder.dose, reminder.id)
            }
            is RecordGlucoseReminder -> {
                notificationManager.postGlucoseMeasuringNotification(reminder)
            }
        }
    }

    companion object {
        const val REMINDER_KEY = "reminder_key"
    }
}