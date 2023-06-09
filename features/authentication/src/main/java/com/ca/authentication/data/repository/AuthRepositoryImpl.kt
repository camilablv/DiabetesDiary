package com.ca.authentication.data.repository

import com.ca.authentication.GoogleAuthenticationProvider
import com.ca.authentication.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authProvider: GoogleAuthenticationProvider) :
    AuthRepository {

    override fun signInWithGoogle() {
        authProvider.signInWithGoogle()
    }
}