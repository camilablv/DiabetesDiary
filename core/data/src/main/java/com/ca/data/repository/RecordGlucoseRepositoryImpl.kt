package com.ca.data.repository

import com.ca.domain.repository.RecordGlucoseRepository
import com.ca.network.api.NetworkClient
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RecordGlucoseRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
) : RecordGlucoseRepository {

    override suspend fun recordGlucose(
        time: LocalTime,
        date: LocalDate,
        note: String,
        mark: String,
        units: Int
    ) {
        val dateTime = LocalDateTime.of(date, time).format(DateTimeFormatter.ISO_DATE_TIME)
        networkClient.recordGlucose(
            dateTime,
            note,
            mark,
            units
        )
    }
}