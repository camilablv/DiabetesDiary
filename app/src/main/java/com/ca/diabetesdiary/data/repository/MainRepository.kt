package com.ca.diabetesdiary.data.repository

import com.ca.authentication.FirebaseAuthProvider
import com.ca.network.api.NetworkClient
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val networkClient: NetworkClient
) {

    val isUserSignedIn: Boolean
        get() = authProvider.isUserSignedIn

    suspend fun isOnBoardingShowed(): Boolean {
        return networkClient.isOnBoardingShowed()
    }
}