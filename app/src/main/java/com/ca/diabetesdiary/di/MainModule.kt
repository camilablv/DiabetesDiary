package com.ca.diabetesdiary.di

import android.app.Activity
import android.content.Context
import com.ca.diabetesdiary.presentation.MainActivity
import com.ca.notification.DiaryNotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    fun provideActivityClass(): Class<out Activity> = MainActivity::class.java

    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context,
        activityClass: Class<out Activity>
    ): DiaryNotificationManager {
        return DiaryNotificationManager(context, activityClass)
    }
}