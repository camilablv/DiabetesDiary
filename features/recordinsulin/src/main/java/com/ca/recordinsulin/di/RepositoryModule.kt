package com.ca.recordinsulin.di

import com.ca.recordinsulin.data.repository.RecordInsulinRepositoryImpl
import com.ca.recordinsulin.domain.repository.RecordInsulinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindRecordInsulinRepository(recordInsulinRepository: RecordInsulinRepositoryImpl): RecordInsulinRepository
}