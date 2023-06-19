package com.ca.authentication.di

import androidx.datastore.core.DataStore
import com.apollographql.apollo3.ApolloClient
import com.ca.authentication.FirebaseAuthProvider
import com.ca.authentication.data.repository.AuthRepositoryImpl
import com.ca.datastore.UserPreferences
import com.ca.network.api.NetworkClient
import com.ca.network.error.NetworkErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class AuthModule {

    @Provides
    fun provideAuthRepository(
        authProvider: FirebaseAuthProvider,
        userPreferencesDataStore: DataStore<UserPreferences>,
        networkClient: NetworkClient
    ) = AuthRepositoryImpl(authProvider, userPreferencesDataStore, networkClient)

    @Provides
    fun provideNetworkClient(apolloClient: ApolloClient, errorHandler: NetworkErrorHandler) =
        NetworkClient(apolloClient, errorHandler)
}