package com.ca.settings.domain.repository

interface SettingsRepository {

    fun linkWithGoogleAccount(token: String, onSuccess: () -> Unit)
    fun isAnonymousSignInMethod(): Boolean
}