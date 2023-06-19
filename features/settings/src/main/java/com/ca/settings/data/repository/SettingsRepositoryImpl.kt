package com.ca.settings.data.repository

import androidx.datastore.core.DataStore
import com.ca.authentication.FirebaseAuthProvider
import com.ca.datastore.Settings
import com.ca.datastore.UserPreferences
import com.ca.network.api.NetworkClient
import com.ca.settings.domain.model.GlucoseUnits
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val networkClient: NetworkClient,
    private val userPreferencesDataStore: DataStore<UserPreferences>,
    private val settingsDataStore: DataStore<Settings>
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
                userPreferencesDataStore.updateData { prefs ->
                    prefs.toBuilder()
                        .setAuthToken(session.token)
                        .setEmail(session.user?.email)
                        .build()
                }
            }
        }
    }

    override suspend fun updateGlucoseUnits(units: GlucoseUnits) {
        settingsDataStore.updateData {
            it.toBuilder()
                .setUnit(Settings.GlucoseUnit.valueOf(units.name))
                .build()
        }
    }

    override suspend fun addInsulin(name: String, color: String, defaultDosage: Int) {
        settingsDataStore.updateData {
            it.toBuilder()
                .addInsulins(
                    Settings.Insulin.newBuilder()
                        .setName(name)
                        .setColor(color)
                        .setDefaultDosage(defaultDosage)
                        .build()
                )
                .build()
        }
    }

    override suspend fun updateInsulin(
        id: String,
        name: String,
        color: String,
        defaultDosage: Int
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInsulin(id: String) {
        TODO("Not yet implemented")
    }
}