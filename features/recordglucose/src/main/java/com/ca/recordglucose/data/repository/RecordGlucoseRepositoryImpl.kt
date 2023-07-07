package com.ca.recordglucose.data.repository

import com.ca.datastore.SettingsDataStore
import com.ca.network.api.NetworkClient
import com.ca.recordglucose.domain.repository.RecordGlucoseRepository
import javax.inject.Inject

class RecordGlucoseRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val dataStore: SettingsDataStore
) : RecordGlucoseRepository {
}