package com.ca.settings.di

import com.ca.domain.repository.InsulinReminderRepository
import com.ca.settings.repository.SettingsRepositoryImpl
import com.ca.domain.repository.SettingsRepository
import com.ca.settings.repository.InsulinReminderRepositoryImpl
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
    fun bindInsulinReminderRepository(repositoryImpl: InsulinReminderRepositoryImpl): InsulinReminderRepository
}