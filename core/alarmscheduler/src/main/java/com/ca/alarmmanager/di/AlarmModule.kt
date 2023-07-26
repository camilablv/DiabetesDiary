package com.ca.alarmmanager.di

import android.content.Context
import com.ca.alarmmanager.AlarmScheduler
import com.ca.alarmmanager.AlarmSchedulerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AlarmModule {

    @Provides
    @Singleton
    fun provideAlarmScheduler(
        @ApplicationContext context: Context
    ): AlarmScheduler {
        return AlarmSchedulerImpl(context)
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context = context
}