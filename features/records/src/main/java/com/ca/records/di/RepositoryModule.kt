package com.ca.records.di

import com.ca.records.domain.repository.GlucoseRecordsRepository
import com.ca.records.domain.repository.InsulinRecordsRepository
import com.ca.records.glucose.data.repository.GlucoseRecordsRepositoryImpl
import com.ca.records.insulin.data.repository.InsulinRecordsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class InsulinRecordsRepository

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GlucoseRecordsRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindInsulinRecordsRepository(repositoryImpl: InsulinRecordsRepositoryImpl): InsulinRecordsRepository

    @Binds
    fun bindGlucoseRecordsRepository(repositoryImpl: GlucoseRecordsRepositoryImpl): GlucoseRecordsRepository
}