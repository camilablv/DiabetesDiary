package com.ca.authentication.token

interface JWTService {
    suspend fun authToken(): String
}