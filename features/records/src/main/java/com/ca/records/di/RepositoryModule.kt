package com.ca.records.di

import com.ca.records.insulin.data.repository.InsulinRecordsRepository
import com.ca.records.domain.repository.RecordsRepository
import com.ca.records.glucose.data.repository.GlucoseRecordsRepository
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
    @com.ca.records.di.InsulinRecordsRepository
    fun bindInsulinRecordsRepository(repositoryImpl: InsulinRecordsRepository): RecordsRepository

    @Binds
    @com.ca.records.di.GlucoseRecordsRepository
    fun bindGlucoseRecordsRepository(repositoryImpl: GlucoseRecordsRepository): RecordsRepository
}