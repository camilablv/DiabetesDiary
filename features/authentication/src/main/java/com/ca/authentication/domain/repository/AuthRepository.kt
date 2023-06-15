package com.ca.authentication.domain.repository

import com.ca.authentication.model.UserData

interface AuthRepository {
    fun createUser(userData: UserData)
    suspend fun signInWithGoogle(token: String)
    suspend fun saveToken(token: String)
}