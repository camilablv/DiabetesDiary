package com.ca.diabetesdiary.data.repository

import com.ca.authentication.FirebaseAuthProvider
import com.ca.diabetesdiary.domain.repository.MainRepository
import com.ca.network.api.NetworkClient
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val networkClient: NetworkClient
) : MainRepository {

    override val isUserSignedIn: Boolean
        get() = authProvider.isUserSignedIn

    override suspend fun isOnBoardingShowed(): Boolean {
        return networkClient.currentUser().fold(
            onFailure = { false },
            onSuccess = { data -> data.user.onboardingCompletedAt != null }
        )
    }
}