package com.ca.database.di

import android.content.Context
import androidx.room.Room
import com.ca.database.DiaryDatabase
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
            .fallbackToDestructiveMigration() //todo remove it later
            .build()
    }
}