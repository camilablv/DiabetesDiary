package com.ca.onboarding.domain.repository

interface OnBoardingRepository {
    suspend fun onBoardingCompleted()
}