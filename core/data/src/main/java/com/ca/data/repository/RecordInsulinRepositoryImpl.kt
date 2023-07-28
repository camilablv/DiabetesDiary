package com.ca.data.repository

import com.ca.datastore.SettingsDataStore
import com.ca.domain.repository.RecordInsulinRepository
import com.ca.model.Insulin
import com.ca.network.api.NetworkClient
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RecordInsulinRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val dataStore: SettingsDataStore
) : RecordInsulinRepository {

    override suspend fun insulins(): List<Insulin> {
        return dataStore.insulins()
    }

    override suspend fun recordInsulin(
        insulinId: String,
        note: String,
        date: LocalDate,
        time: LocalTime,
        dose: Int
    ) {
        val dateTme = LocalDateTime.of(date, time).format(DateTimeFormatter.ISO_DATE_TIME)
        networkClient.recordInsulin(insulinId, note, dateTme.toString(), dose)
    }
}