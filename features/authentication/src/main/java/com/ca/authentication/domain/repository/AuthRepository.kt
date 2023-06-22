package com.ca.authentication.domain.repository


interface AuthRepository {
    suspend fun createSession(idToken: String, onSuccess: suspend () -> Unit)
    suspend fun signInWithGoogle(token: String)
}