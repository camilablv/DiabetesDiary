package com.ca.notification

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.ca.domain.model.RecordGlucoseReminder
import com.ca.domain.model.RecordInsulinReminder
import javax.inject.Inject

class DiaryNotificationManager @Inject constructor(
    private val context: Context,
    activityClass: Class<out Activity>
) {

    private val intent = Intent(context, activityClass).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    private val pendingIntent by lazy {
        PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Records reminders"
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun showRecordInsulinNotification(
        reminder: RecordInsulinReminder
    ) {
        val recordInsulinPendingIntent = insulinActionPendingIntent(reminder.insulinId, reminder.dose, reminder.id)

        intent.apply {
            putExtra("start_type", "insulin_notification")
        }

        postReminderNotification(
            NotificationCompat.Builder(context, CHANNEL_ID)
                .addAction(R.drawable.baseline_edit_24, "Done", recordInsulinPendingIntent),
            "Record insulin",
            "It's time to take insulin: ${reminder.dose} UN",
            reminder.id
        )
    }

    fun postGlucoseMeasuringNotification(reminder: RecordGlucoseReminder) {
        postReminderNotification(
            NotificationCompat.Builder(context, CHANNEL_ID),
            "Record glucose level",
            "It's time to measure glucose level",
            reminder.id
        )
    }

    private fun postReminderNotification(
        notificationBuilder: NotificationCompat.Builder,
        title: String,
        text: String,
        reminderId: Int
    ) {
        notificationBuilder
            .setSmallIcon(R.drawable.round_alarm_24)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) return
            notify(reminderId, notificationBuilder.build())
        }
    }

    fun cancelNotification(notificationId: Int) {
        notificationManager.cancel(notificationId)
    }

    private fun insulinActionPendingIntent(
        insulinId: String,
        dose: Int,
        notificationId: Int
    ): PendingIntent? {
        val recordInsulinIntent =
            Intent(context, RecordInsulinReceiver::class.java).apply {
                action = RecordInsulinReceiver.ACTION_INSULIN_TAKEN
                putExtra(RecordInsulinReceiver.INSULIN_ID_KEY, insulinId)
                putExtra(RecordInsulinReceiver.DOSE_KEY, dose)
                putExtra(RecordInsulinReceiver.NOTIFICATION_ID, notificationId)
            }
        return PendingIntent.getBroadcast(
            context,
            insulinId.hashCode(),
            recordInsulinIntent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    companion object {
        private const val CHANNEL_ID = "notification_channel_id"
        private const val CHANNEL_NAME = "Reminders"
    }
}