package com.ca.settings.domain.repository

interface SettingsRepository {

    val isAnonymousSignInMethod: Boolean
    fun linkWithGoogleAccount(token: String, onSuccess: () -> Unit)
    suspend fun createSession(token: String)
}