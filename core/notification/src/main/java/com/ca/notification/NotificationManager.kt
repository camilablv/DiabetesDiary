package com.ca.notification

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import javax.inject.Inject

class NotificationManager @Inject constructor(
    private val context: Context,
    activityClass: Class<out Activity>
) {

    private val intent = Intent(context, activityClass).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    private val pendingIntent by lazy {
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager

    fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            android.app.NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Description"
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun createRecordInsulinNotification(
        insulinId: String,
        dose: Int,
        notificationId: Int
    ) {
        val recordInsulinPendingIntent = insulinActionPendingIntent(insulinId, dose)

        intent.apply {
            putExtra("start_type", "insulin_notification")
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.round_alarm_24)
            .setContentTitle("Record insulin")
            .setContentText("It's time to take insulin: $dose UN")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(R.drawable.baseline_edit_24, "Done", recordInsulinPendingIntent)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) return
            notify(notificationId, builder.build())
        }
    }

    private fun insulinActionPendingIntent(insulinId: String, dose: Int,): PendingIntent? {
        val recordInsulinIntent = Intent(context, RecordInsulinBroadcastReceiver::class.java).apply {
            action = RecordInsulinBroadcastReceiver.ACTION_INSULIN_TAKEN
            putExtra(RecordInsulinBroadcastReceiver.INSULIN_ID_KEY, insulinId)
            putExtra(RecordInsulinBroadcastReceiver.DOSE_KEY, dose)
        }
        return PendingIntent.getBroadcast(context, 0, recordInsulinIntent, 0)
    }

    companion object {
        private const val CHANNEL_ID = "notification_channel_id"
        private const val CHANNEL_NAME = "Reminders"
    }
}