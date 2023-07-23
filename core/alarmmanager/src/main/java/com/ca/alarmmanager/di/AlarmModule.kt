package com.ca.alarmmanager.di

import android.content.Context
import com.ca.alarmmanager.ReminderAlarmManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AlarmModule {

    @Provides
    fun provideAlarmManager(@ApplicationContext context: Context): ReminderAlarmManager {
        return ReminderAlarmManager(context)
    }
}