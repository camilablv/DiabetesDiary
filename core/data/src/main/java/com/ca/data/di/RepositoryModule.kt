package com.ca.data.di

import com.ca.data.repository.*
import com.ca.domain.repository.*
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

    @Binds
    fun bindRecordGlucoseRepository(recordGlucoseRepository: RecordGlucoseRepositoryImpl): RecordGlucoseRepository

    @Binds
    fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}