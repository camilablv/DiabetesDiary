package com.ca.diabetesdiary.di

import com.ca.authentication.FirebaseAuthProvider
import com.ca.diabetesdiary.data.repository.MainRepositoryImpl
import com.ca.diabetesdiary.domain.repository.MainRepository
import com.ca.network.api.NetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MainModule {

    @Provides
    fun provideMainRepository(
        authProvider: FirebaseAuthProvider,
        networkClient: NetworkClient
    ): MainRepository {
        return MainRepositoryImpl(
            authProvider = authProvider,
            networkClient = networkClient
        )
    }
}