package com.ca.data.repository

import com.ca.database.dao.GlucoseRecordsDao
import com.ca.database.model.asEntity
import com.ca.database.model.asExternalModel
import com.ca.domain.repository.RecordGlucoseRepository
import com.ca.model.GlucoseRecord
import com.ca.network.api.NetworkClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RecordGlucoseRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val glucoseRecordsDao: GlucoseRecordsDao
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

    override suspend fun addRecord(record: GlucoseRecord) {
        glucoseRecordsDao.insert(record.asEntity())
    }

    override suspend fun records(): Flow<List<GlucoseRecord>> {
        return glucoseRecordsDao
            .glucoseRecords()
            .map { list -> list.map { it.asExternalModel() } }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun recordsByDate(date: LocalDate): Flow<List<GlucoseRecord>> {
        return glucoseRecordsDao
            .recordsByDate(date)
            .map { list -> list.map { it.asExternalModel() } }
            .flowOn(Dispatchers.IO)
    }
}