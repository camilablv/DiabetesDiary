package com.ca.records.data.repository

import com.ca.datastore.SettingsDataStore
import com.ca.network.api.NetworkClient
import com.ca.model.InsulinRecord
import com.ca.network.utils.records
import com.ca.records.domain.repository.RecordsRepository
import javax.inject.Inject

class RecordsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val settingsDataStore: SettingsDataStore
) : RecordsRepository {

    override suspend fun insulinRecords(cursor: String?, limit: Int): List<InsulinRecord?>? {
        return networkClient.insulinRecords(cursor, limit).fold(
            onSuccess = { data -> data.records() },
            onFailure = {
                null
            }
        )
    }
}