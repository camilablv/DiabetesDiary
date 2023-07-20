package com.ca.reminders.recordinsulineminder.di

import com.ca.reminders.recordinsulineminder.data.repository.InsulinReminderRepositoryImpl
import com.ca.reminders.recordinsulineminder.domain.repository.InsulinReminderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindInsulinReminderRepository(repositoryImpl: InsulinReminderRepositoryImpl): InsulinReminderRepository
}