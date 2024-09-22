package com.ca.network.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.ca.authentication.token.JWTService
import com.ca.network.error.NetworkErrorHandler
import com.ca.network.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideApolloClient(
        jwtService: JWTService
    ): ApolloClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(jwtService))
            .build()

        return ApolloClient.Builder()
            .serverUrl("http://192.168.0.105:4000/graph")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    fun provideNetworkErrorHandler() = NetworkErrorHandler()
}