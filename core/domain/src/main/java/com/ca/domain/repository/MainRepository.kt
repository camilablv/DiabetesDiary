package com.ca.domain.repository

import kotlinx.coroutines.flow.Flow

interface MainRepository {
    val isUserSignedIn: Boolean
    suspend fun isOnBoardingShowed(): Boolean
    suspend fun darkMode(): Flow<Boolean>
    suspend fun fetchRemoteSettings()
}