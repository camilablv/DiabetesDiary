package com.ca.settings.data.repository

import com.ca.authentication.FirebaseAuthProvider
import com.ca.data.model.GlucoseUnits
import com.ca.data.model.Insulin
import com.ca.datastore.SettingsDataStore
import com.ca.datastore.UserDataStore
import com.ca.network.api.NetworkClient
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val networkClient: NetworkClient,
    private val userPreferencesDataStore: UserDataStore,
    private val settingsDataStore: SettingsDataStore
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
                userPreferencesDataStore.updateUserData(
                    token = session.token,
                    email = session.user?.email
                )
            }
        }
    }

    override suspend fun updateGlucoseUnits(units: GlucoseUnits) {
        settingsDataStore.updateGlucoseUnits(units)
    }

    override suspend fun addInsulin(insulin: Insulin) {
        settingsDataStore.addInsulin(insulin)
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