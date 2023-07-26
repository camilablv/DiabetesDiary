package com.ca.diabetesdiary.di

import android.content.Context
import com.ca.authentication.FirebaseAuthProvider
import com.ca.diabetesdiary.data.repository.MainRepositoryImpl
import com.ca.diabetesdiary.domain.repository.MainRepository
import com.ca.network.api.NetworkClient
import com.ca.notification.NotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

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

    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManager {
        return NotificationManager(context)
    }
}