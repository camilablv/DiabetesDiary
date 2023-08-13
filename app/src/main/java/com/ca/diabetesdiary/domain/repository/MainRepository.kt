package com.ca.diabetesdiary.domain.repository

import kotlinx.coroutines.flow.Flow

interface MainRepository {
    val isUserSignedIn: Boolean
    suspend fun isOnBoardingShowed(): Boolean
    fun darkMode(): Flow<Boolean>
    suspend fun fetchRemoteSettings()
}