package com.ca.data.repository

import android.util.Log
import com.ca.datastore.SettingsDataStore
import com.ca.datastore.UserDataStore
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import com.ca.network.api.NetworkClient
import com.ca.network.utils.insulin
import com.ca.network.utils.unit
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val userPreferencesDataStore: UserDataStore,
    private val settingsDataStore: SettingsDataStore
) : com.ca.domain.repository.SettingsRepository {

    override suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits? {
        return networkClient.updateGlucoseUnit(units).fold(
            onSuccess = { settingsDataStore.updateGlucoseUnits(it.unit()) },
            onFailure = { null }
        )
    }

    override suspend fun addInsulin(name: String, color: String, defaultDose: Int): List<Insulin>? {
        return networkClient.createInsulin(name, color, defaultDose).fold(
            onSuccess = { settingsDataStore.addInsulin(it.insulin()) },
            onFailure = {
                Log.d("SettingsRepositoryImpl", it.message.toString())
                null
            }
        )
    }

    override suspend fun updateInsulin(
        id: String,
        name: String,
        color: String,
        defaultDosage: Int
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteInsulin(id: String): List<Insulin> {
        //todo add delayed sending to the server if there is no internet
        networkClient.deleteInsulin(id)
        return settingsDataStore.deleteInsulin(id)
    }

    override suspend fun insulins(): List<Insulin> = settingsDataStore.insulins()
}