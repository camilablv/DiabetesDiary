package com.ca.records.di

import com.ca.records.data.repository.RecordsRepositoryImpl
import com.ca.records.domain.repository.RecordsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindRecordsRepository(repositoryImpl: RecordsRepositoryImpl): RecordsRepository
}