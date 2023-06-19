package com.ca.authentication.di

import com.ca.authentication.FirebaseAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal class FirebaseAuthModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideAuthProvider(firebaseAuth: FirebaseAuth): FirebaseAuthProvider {
        return FirebaseAuthProvider(firebaseAuth)
    }
}