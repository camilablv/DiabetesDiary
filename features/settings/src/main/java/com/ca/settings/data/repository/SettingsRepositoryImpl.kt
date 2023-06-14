package com.ca.settings.data.repository

import com.ca.authentication.GoogleAuthenticationProvider
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val googleAuthProvider: GoogleAuthenticationProvider
) : SettingsRepository {

    override fun linkWithGoogleAccount(
        token: String,
        onSuccess: () -> Unit
    ) {
        googleAuthProvider.linkWithGoogle(token, onSuccess)
    }

    override fun isAnonymousSignInMethod(): Boolean = googleAuthProvider.signInMethod()
}