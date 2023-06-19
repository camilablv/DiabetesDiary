package com.ca.settings.domain.repository

interface SettingsRepository {

    val isAnonymousSignInMethod: Boolean
    suspend fun linkWithGoogleAccount(token: String, onSuccess: () -> Unit)
}