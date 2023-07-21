package com.ca.database.di

import android.content.Context
import androidx.room.Room
import com.ca.database.db.ReminderDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ReminderDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = ReminderDataBase::class.java,
            name = "reminder_database"
        )
            .fallbackToDestructiveMigration() //todo remove it later
            .build()
    }
}