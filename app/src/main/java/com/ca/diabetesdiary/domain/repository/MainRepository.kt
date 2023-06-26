package com.ca.diabetesdiary.domain.repository

interface MainRepository {
    val isUserSignedIn: Boolean
    suspend fun isOnBoardingShowed(): Boolean
}