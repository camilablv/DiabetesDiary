package com.ca.data.repository

import com.ca.data.di.IoDispatcher
import com.ca.database.dao.GlucoseRecordsDao
import com.ca.database.model.asEntity
import com.ca.database.model.asExternalModel
import com.ca.model.GlucoseRecord
import com.ca.domain.repository.RecordGlucoseRepository
import com.ca.network.api.NetworkClient
import com.ca.network.utils.record
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class RecordGlucoseRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val glucoseRecordsDao: GlucoseRecordsDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RecordGlucoseRepository {

    override suspend fun createRecord(
        time: LocalTime,
        date: LocalDate,
        note: String?,
        mark: String,
        units: Int
    ) {
        val dateTime = LocalDateTime.of(date, time).format(DateTimeFormatter.ISO_DATE_TIME)
        withContext(ioDispatcher) {
            networkClient.recordGlucose(dateTime, note, mark, units).fold(
                onSuccess = {
                    addRecord(it.record())
                },
                onFailure = {}
            )
        }
    }

    override suspend fun addRecord(record: GlucoseRecord) = withContext(ioDispatcher) {
        glucoseRecordsDao.insert(record.asEntity())
    }

    override suspend fun updateRecord(record: GlucoseRecord) = withContext(ioDispatcher) {
        glucoseRecordsDao.update(record.asEntity())
    }

    override suspend fun records(): Flow<List<GlucoseRecord>> {
        return glucoseRecordsDao
            .glucoseRecords()
            .map { list -> list.map { it.asExternalModel() } }
            .flowOn(ioDispatcher)
    }

    override suspend fun recordsByDate(date: LocalDate): Flow<List<GlucoseRecord>> {
        return glucoseRecordsDao
            .recordsByDate(date)
            .map { list -> list.map { it.asExternalModel() } }
            .flowOn(ioDispatcher)
    }

    override suspend fun delete(record: GlucoseRecord) = withContext(ioDispatcher) {
        glucoseRecordsDao.delete(record.asEntity())
    }

    override suspend fun recordById(id: String): GlucoseRecord {
        return withContext(ioDispatcher) {
            glucoseRecordsDao.recordById(id).asExternalModel()
        }
    }
}