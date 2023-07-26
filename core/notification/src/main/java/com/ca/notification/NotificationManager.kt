package com.ca.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import javax.inject.Inject

class NotificationManager @Inject constructor(
    private val context: Context
) {

//    private val intent by lazy {
//        Intent(context, activityClass).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//    }
//
//    private val pendingIntent by lazy {
//        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//    }

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager

    fun createNotificationChannel() {
        val channel = NotificationChannel(
            "channel_id",
            "channel_name",
            android.app.NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Description"
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun createNotification() {

        val builder = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(R.drawable.round_pest_control_24)
            .setContentTitle("Title")
            .setContentText("Text")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(1, builder.build())
        }
    }
}