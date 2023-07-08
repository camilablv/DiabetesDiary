package com.ca.recordglucose.data.repository

import com.ca.datastore.SettingsDataStore
import com.ca.network.api.NetworkClient
import com.ca.recordglucose.domain.model.MeasuringMark
import com.ca.recordglucose.domain.repository.RecordGlucoseRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RecordGlucoseRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val dataStore: SettingsDataStore
) : RecordGlucoseRepository {

    override suspend fun recordGlucose(
        time: LocalTime,
        date: LocalDate,
        note: String,
        mark: MeasuringMark,
        units: Int
    ) {
        val dateTime = LocalDateTime.of(date, time).format(DateTimeFormatter.ISO_DATE_TIME)
        networkClient.recordGlucose(
            dateTime,
            note,
            mark.name,
            units
        )
    }
}