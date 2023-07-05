package com.ca.recordinsulin.data.repository

import com.ca.datastore.SettingsDataStore
import com.ca.model.Insulin
import com.ca.network.api.NetworkClient
import com.ca.recordinsulin.domain.repository.RecordInsulinRepository
import javax.inject.Inject

class RecordInsulinRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val dataStore: SettingsDataStore
) : RecordInsulinRepository {

    override suspend fun insulins(): List<Insulin> {
        return dataStore.insulins()
    }
}