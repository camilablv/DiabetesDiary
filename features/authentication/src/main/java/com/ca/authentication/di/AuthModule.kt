package com.ca.authentication.di

import androidx.datastore.core.DataStore
import com.ca.authentication.GoogleAuthenticationProvider
import com.ca.authentication.data.repository.AuthRepositoryImpl
import com.ca.datastore.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    fun provideAuthRepository(
        googleAuthenticationProvider: GoogleAuthenticationProvider,
        userPreferencesDataStore: DataStore<UserPreferences>
    ) = AuthRepositoryImpl(googleAuthenticationProvider, userPreferencesDataStore)

    @Provides
    fun provideGoogleAuthProvider() = GoogleAuthenticationProvider()
}