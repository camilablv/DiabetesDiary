package com.ca.diabetesdiary.data.repository

import android.util.Log
import com.ca.authentication.FirebaseAuthProvider
import com.ca.datastore.SettingsDataStore
import com.ca.diabetesdiary.domain.repository.MainRepository
import com.ca.network.api.NetworkClient
import com.ca.network.utils.settings
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val authProvider: FirebaseAuthProvider,
    private val networkClient: NetworkClient,
    private val settingsDataStore: SettingsDataStore
) : MainRepository {

    override val isUserSignedIn: Boolean
        get() = authProvider.isUserSignedIn

    override suspend fun isOnBoardingShowed(): Boolean {
        return networkClient.currentUser().fold(
            onFailure = { false },
            onSuccess = { data -> data.user.onboardingCompletedAt != null }
        )
    }

    override fun darkMode(): Flow<Boolean> = settingsDataStore.darkMode()

    override suspend fun fetchRemoteSettings() {
        networkClient.settings().fold(
            onSuccess = { data ->
                with(data.settings()) {
                    Log.d("insulins", insulins.size.toString())
                    insulins.forEach { insulin ->
                        networkClient.deleteInsulin(insulin.id).fold(
                            onSuccess = { Log.d("insulins", "onSuccess ${insulin.id}") },
                            onFailure = { Log.d("insulins", "onFailure $it") }
                        )
                    }
                    settingsDataStore.updateGlucoseUnits(glucoseUnits)

                }
            },
            onFailure = {}
        )
    }
}