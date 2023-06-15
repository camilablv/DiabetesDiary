package com.ca.authentication.di

import androidx.datastore.core.DataStore
import com.apollographql.apollo3.ApolloClient
import com.ca.authentication.GoogleAuthenticationProvider
import com.ca.authentication.data.repository.AuthRepositoryImpl
import com.ca.authentication.data.network.NetworkClient
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
        userPreferencesDataStore: DataStore<UserPreferences>,
        networkClient: NetworkClient
    ) = AuthRepositoryImpl(googleAuthenticationProvider, userPreferencesDataStore, networkClient)

    @Provides
    fun provideGoogleAuthProvider() = GoogleAuthenticationProvider()

    @Provides
    fun provideNetworkClient(apolloClient: ApolloClient) = NetworkClient(apolloClient)
}