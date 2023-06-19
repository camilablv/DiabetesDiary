package com.ca.settings.data.repository

import androidx.datastore.core.DataStore
import com.ca.authentication.FirebaseAuthProvider
import com.ca.datastore.UserPreferences
import com.ca.network.api.NetworkClient
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val networkClient: NetworkClient,
    private val dataStore: DataStore<UserPreferences>
) : SettingsRepository {

    override val isAnonymousSignInMethod: Boolean
        get() = authProvider.isAnonymousSignInMethod

    override fun linkWithGoogleAccount(
        token: String,
        onSuccess: () -> Unit
    ) {
        authProvider.linkWithGoogle(token, onSuccess)
    }

    override suspend fun createSession(token: String) {
        networkClient.createSession(token).onSuccess { data ->
            data.session?.let { session ->
                dataStore.updateData { prefs ->
                    prefs.toBuilder()
                        .setAuthToken(session.token)
                        .setEmail(session.user?.email)
                        .build()
                }
            }
        }
    }
}