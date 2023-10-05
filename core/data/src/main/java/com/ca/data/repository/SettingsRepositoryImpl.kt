package com.ca.data.repository

import android.util.Log
import com.ca.data.di.IoDispatcher
import com.ca.datastore.SettingsDataStore
import com.ca.datastore.UserDataStore
import com.ca.domain.model.GlucoseUnits
import com.ca.domain.model.Insulin
import com.ca.domain.model.Settings
import com.ca.network.api.NetworkClient
import com.ca.network.utils.insulin
import com.ca.network.utils.unit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val userPreferencesDataStore: UserDataStore,
    private val settingsDataStore: SettingsDataStore,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : com.ca.domain.repository.SettingsRepository {

    override suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits? {
        return withContext(ioDispatcher) {
            networkClient.updateGlucoseUnit(units).fold(
                onSuccess = { settingsDataStore.updateGlucoseUnits(it.unit()) },
                onFailure = { null }
            )
        }
    }

    override suspend fun addInsulin(name: String, color: String, defaultDose: Int): List<Insulin>? {
        return withContext(ioDispatcher) {
            networkClient.createInsulin(name, color, defaultDose).fold(
                onSuccess = { settingsDataStore.addInsulin(it.insulin()) },
                onFailure = {
                    Log.d("SettingsRepositoryImpl", it.message.toString())
                    null
                }
            )
        }
    }

    override suspend fun updateInsulin(
        id: String,
        name: String,
        color: String,
        defaultDosage: Int
    ) {
        withContext(ioDispatcher) {
            networkClient.updateInsulin(id, name, color, defaultDosage).fold(
                onSuccess = { settingsDataStore.updateInsulin(it.insulin()) },
                onFailure = {
                    Log.d("SettingsRepositoryImpl", "updateInsulin " + it.message.toString())
                }
            )
        }
    }

    override suspend fun deleteInsulin(id: String) {
        //todo add delayed sending to the server if there is no internet
        return withContext(ioDispatcher) {
            networkClient.deleteInsulin(id).fold(
                onSuccess = { settingsDataStore.deleteInsulin(id) },
                onFailure = {}
            )
        }
    }

    override suspend fun insulins(): List<Insulin> = withContext(ioDispatcher) {
        settingsDataStore.insulins()
    }

    override suspend fun insulinsFlow(): Flow<List<Insulin>> {
        return settingsDataStore.insulinsFlow()
    }

    override suspend fun settings(): Flow<Settings> =
        settingsDataStore.settings().flowOn(Dispatchers.IO)

    override suspend fun darkMode(darkMode: Boolean) = withContext(ioDispatcher) {
        settingsDataStore.setDarkMode(darkMode)
    }
}