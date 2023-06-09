package com.ca.authentication.di

import com.ca.authentication.GoogleAuthenticationProvider
import com.ca.authentication.data.repository.AuthRepositoryImpl
import com.ca.authentication.domain.repository.AuthRepository
import com.ca.authentication.domain.usecase.SignInWithGoogleUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    fun provideSignInWithGoogleUseCase(repository: AuthRepository) =
        SignInWithGoogleUseCase(repository)

    @Provides
    fun provideAuthRepository(authenticationProvider: GoogleAuthenticationProvider) =
        AuthRepositoryImpl(authenticationProvider)

    @Provides
    fun provideGoogleAuthProvider() = GoogleAuthenticationProvider()
}