package com.ca.diabetesdiary.di

import android.app.Activity
import android.content.Context
import com.ca.authentication.FirebaseAuthProvider
import com.ca.diabetesdiary.data.repository.MainRepositoryImpl
import com.ca.diabetesdiary.domain.repository.MainRepository
import com.ca.diabetesdiary.presentation.MainActivity
import com.ca.network.api.NetworkClient
import com.ca.notification.NotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
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
    fun provideActivityClass(): Class<out Activity> = MainActivity::class.java

    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext context: Context,
        activityClass: Class<out Activity>
    ): NotificationManager {
        return NotificationManager(context, activityClass)
    }
}