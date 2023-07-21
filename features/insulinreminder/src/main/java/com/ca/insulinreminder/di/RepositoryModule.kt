package com.ca.insulinreminder.di

import com.ca.insulinreminder.data.InsulinReminderRepositoryImpl
import com.ca.insulinreminder.domain.InsulinReminderRepository
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