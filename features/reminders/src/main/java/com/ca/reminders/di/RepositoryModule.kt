package com.ca.reminders.di

import com.ca.reminders.data.repository.RemindersRepositoryImpl
import com.ca.reminders.domain.repository.RemindersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindReminderRepository(repositoryImpl: RemindersRepositoryImpl): RemindersRepository
}