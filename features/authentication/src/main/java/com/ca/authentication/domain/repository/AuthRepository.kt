package com.ca.authentication.domain.repository

interface AuthRepository {

    suspend fun saveToken(token: String)
}