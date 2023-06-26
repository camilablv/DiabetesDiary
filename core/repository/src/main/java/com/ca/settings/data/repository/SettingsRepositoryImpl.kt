package com.ca.settings.data.repository

import com.ca.datastore.SettingsDataStore
import com.ca.datastore.UserDataStore
import com.ca.network.api.NetworkClient
import com.ca.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val userPreferencesDataStore: UserDataStore,
    private val settingsDataStore: SettingsDataStore
) : SettingsRepository {

    override suspend fun updateGlucoseUnits(units: com.ca.model.GlucoseUnits): com.ca.model.GlucoseUnits {
        return settingsDataStore.updateGlucoseUnits(units)
    }

    override suspend fun addInsulin(insulin: com.ca.model.Insulin): List<com.ca.model.Insulin> {
        return settingsDataStore.addInsulin(insulin)
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