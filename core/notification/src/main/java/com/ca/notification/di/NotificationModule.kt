package com.ca.notification.di

import android.content.Context
import androidx.activity.ComponentActivity
import com.ca.notification.NotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {

//    @Provides
//    @Singleton
//    fun provideNotificationManager(
//        @ApplicationContext context: Context
//    ): NotificationManager {
//        return NotificationManager(context)
//    }
}