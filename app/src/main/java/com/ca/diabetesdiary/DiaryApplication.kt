package com.ca.diabetesdiary

import android.app.Application
import com.ca.notification.DiaryNotificationManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DiaryApplication : Application() {

    @Inject lateinit var notificationManager: DiaryNotificationManager

    override fun onCreate() {
        super.onCreate()
        notificationManager.createNotificationChannel()
    }
}