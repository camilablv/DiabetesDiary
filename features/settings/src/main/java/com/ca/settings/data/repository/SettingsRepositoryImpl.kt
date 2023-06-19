package com.ca.settings.data.repository

import com.ca.authentication.FirebaseAuthProvider
import com.ca.network.api.NetworkClient
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val networkClient: NetworkClient
) : SettingsRepository {

    override val isAnonymousSignInMethod: Boolean
        get() = authProvider.isAnonymousSignInMethod

    override suspend fun linkWithGoogleAccount(
        token: String,
        onSuccess: () -> Unit
    ) {
        authProvider.linkWithGoogle(token, onSuccess)
        networkClient.createUser(token)
    }
}