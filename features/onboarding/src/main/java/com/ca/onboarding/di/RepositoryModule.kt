package com.ca.onboarding.di

import com.ca.onboarding.data.repository.OnBoardingRepositoryImpl
import com.ca.onboarding.domain.repository.OnBoardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindOnBoardingRepository(onBoardingRepository: OnBoardingRepositoryImpl): OnBoardingRepository
}