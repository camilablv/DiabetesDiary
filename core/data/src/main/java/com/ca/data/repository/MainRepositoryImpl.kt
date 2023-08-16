package com.ca.data.repository

import android.util.Log
import com.ca.authentication.FirebaseAuthProvider
import com.ca.data.di.IoDispatcher
import com.ca.datastore.SettingsDataStore
import com.ca.domain.repository.MainRepository
import com.ca.network.api.NetworkClient
import com.ca.network.utils.settings
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val networkClient: NetworkClient,
    private val settingsDataStore: SettingsDataStore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MainRepository {

    override val isUserSignedIn: Boolean
        get() = authProvider.isUserSignedIn

    override suspend fun isOnBoardingShowed(): Boolean {
        return withContext(ioDispatcher) {
            networkClient.currentUser().fold(
                onFailure = { false },
                onSuccess = { data -> data.user.onboardingCompletedAt != null }
            )
        }
    }

    override suspend fun darkMode(): Flow<Boolean> = withContext(ioDispatcher) {
        settingsDataStore.darkMode()
    }

    override suspend fun fetchRemoteSettings() {
        withContext(ioDispatcher) {
            networkClient.settings().fold(
                onSuccess = { data ->
                    with(data.settings()) {
                        Log.d("insulins", insulins.size.toString())
                        insulins.forEach { insulin ->
                            settingsDataStore.updateInsulin(insulin)
                        }
                        settingsDataStore.updateGlucoseUnits(glucoseUnits)

                    }
                },
                onFailure = {}
            )
        }
    }
}