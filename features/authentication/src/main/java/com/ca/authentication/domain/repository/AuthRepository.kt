package com.ca.authentication.domain.repository


interface AuthRepository {
    suspend fun createSession(idToken: String)
    suspend fun signInWithGoogle(token: String)
}