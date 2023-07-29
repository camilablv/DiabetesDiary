package com.ca.data.di

import com.ca.domain.repository.RemindersRepository
import com.ca.data.repository.SettingsRepositoryImpl
import com.ca.domain.repository.SettingsRepository
import com.ca.data.repository.InsulinReminderRepositoryImpl
import com.ca.data.repository.RecordInsulinRepositoryImpl
import com.ca.domain.repository.RecordInsulinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    fun bindInsulinReminderRepository(repositoryImpl: InsulinReminderRepositoryImpl): RemindersRepository

    @Binds
    fun bindRecordInsulinRepository(recordInsulinRepository: RecordInsulinRepositoryImpl): RecordInsulinRepository
}