package com.ca.recordglucose.di

import com.ca.recordglucose.data.repository.RecordGlucoseRepositoryImpl
import com.ca.recordglucose.domain.repository.RecordGlucoseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindRecordInsulinRepository(repositoryImpl: RecordGlucoseRepositoryImpl): RecordGlucoseRepository
}