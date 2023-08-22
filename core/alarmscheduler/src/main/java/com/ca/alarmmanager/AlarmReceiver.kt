package com.ca.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ca.notification.DiaryNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {

    @Inject lateinit var notificationManager: DiaryNotificationManager

    override fun onReceive(context: Context?, intent: Intent) {
        Log.d("EXTRA_MESSAGE", "Received message")

        val insulinId = intent.getStringExtra(INSULIN_ID_KEY)!!
        val dose = intent.getIntExtra(DOSE_KEY, 0)
        val reminderId = intent.getIntExtra(REMINDER_ID_KEY, 0)

        notificationManager.createNotificationChannel()

        Log.d("EXTRA_MESSAGE", reminderId.toString())

        notificationManager.showRecordInsulinNotification(insulinId, dose, reminderId)
    }

    companion object {
        const val INSULIN_ID_KEY = "insulin_id_key"
        const val DOSE_KEY = "dose_key"
        const val REMINDER_ID_KEY = "reminder_id_key"
    }
}