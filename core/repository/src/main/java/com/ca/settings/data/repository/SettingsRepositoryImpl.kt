package com.ca.settings.data.repository

import android.util.Log
import com.ca.datastore.SettingsDataStore
import com.ca.datastore.UserDataStore
import com.ca.model.GlucoseUnits
import com.ca.model.Insulin
import com.ca.network.api.NetworkClient
import com.ca.network.utils.insulin
import com.ca.network.utils.unit
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val userPreferencesDataStore: UserDataStore,
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {

    override suspend fun updateGlucoseUnits(units: GlucoseUnits): GlucoseUnits {
        val result = networkClient.updateGlucoseUnit(units)
        return settingsDataStore.updateGlucoseUnits(result.getOrNull()?.unit()!!)
    }

    override suspend fun addInsulin(name: String, color: String, defaultDose: Int): List<Insulin> {
        val result = networkClient.createInsulin(name, color, defaultDose)
        result.onFailure { Log.d("SettingsRepositoryImpl", it.message.toString()) }
        return settingsDataStore.addInsulin(result.getOrNull()?.insulin()!!)
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
        networkClient.deleteInsulin(id)
        return settingsDataStore.deleteInsulin(id)
    }

    override suspend fun insulins(): List<Insulin> = settingsDataStore.insulins()
}