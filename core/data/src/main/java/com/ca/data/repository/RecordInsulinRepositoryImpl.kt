package com.ca.data.repository

import com.ca.database.dao.InsulinRecordsDao
import com.ca.database.model.asEntity
import com.ca.database.model.asExternalModel
import com.ca.datastore.SettingsDataStore
import com.ca.domain.repository.RecordInsulinRepository
import com.ca.model.Insulin
import com.ca.model.InsulinRecord
import com.ca.network.api.NetworkClient
import com.ca.network.utils.record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RecordInsulinRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val dataStore: SettingsDataStore,
    private val insulinRecordsDao: InsulinRecordsDao
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
        return networkClient.recordInsulin(insulinId, note, dateTme.toString(), dose).fold(
            onSuccess = { data ->
                addRecord(data.record(insulins().find { it.id == insulinId }!!))
            },
            onFailure = {}
        )
    }

    override suspend fun addRecord(record: InsulinRecord) {
        insulinRecordsDao.insert(record.asEntity())
    }

    override suspend fun records(): Flow<List<InsulinRecord>> {
        return insulinRecordsDao
            .insulinRecords()
            .map { list -> list.map { it.asExternalModel() } }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun recordsByDate(date: LocalDate): Flow<List<InsulinRecord>> {
        return insulinRecordsDao
            .recordsByDate(date)
            .map { list -> list.map { it.asExternalModel() } }
            .flowOn(Dispatchers.IO)
    }
}