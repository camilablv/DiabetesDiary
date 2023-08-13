package com.ca.database.di

import android.content.Context
import androidx.room.Room
import com.ca.database.DiaryDatabase
import com.ca.database.dao.GlucoseRecordsDao
import com.ca.database.dao.GlucoseReminderDao
import com.ca.database.dao.InsulinRecordsDao
import com.ca.database.dao.InsulinReminderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DiaryDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = DiaryDatabase::class.java,
            name = "reminder_database"
        )
            .fallbackToDestructiveMigration() //TODO remove it later
            .build()
    }

    @Singleton
    @Provides
    fun provideInsulinReminderDao(database: DiaryDatabase): InsulinReminderDao = database.insulinReminderDao()

    @Singleton
    @Provides
    fun provideGlucoseReminderDao(database: DiaryDatabase): GlucoseReminderDao = database.glucoseReminderDao()

    @Singleton
    @Provides
    fun provideInsulinRecordsDao(database: DiaryDatabase): InsulinRecordsDao = database.insulinRecordsDao()

    @Singleton
    @Provides
    fun provideGlucoseRecordsDao(database: DiaryDatabase): GlucoseRecordsDao = database.glucoseRecordsDao()
}