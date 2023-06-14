package com.ca.authentication.domain.repository

interface AuthRepository {

    suspend fun signInWithGoogle(token: String)
    suspend fun saveToken(token: String)
}