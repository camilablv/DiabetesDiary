package com.ca.data.di

import com.ca.data.datasource.locale.LocaleDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideLocaleDataSource() = LocaleDataSource()
}