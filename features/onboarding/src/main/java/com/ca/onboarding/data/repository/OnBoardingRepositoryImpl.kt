package com.ca.onboarding.data.repository

import com.ca.network.api.NetworkClient
import com.ca.onboarding.domain.repository.OnBoardingRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
) : OnBoardingRepository {

    override suspend fun onBoardingCompleted() {
        val time = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        networkClient.completeOnBoarding(time.toString())
    }
}