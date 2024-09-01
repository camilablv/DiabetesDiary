package com.ca.records.glucose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ca.database.dao.GlucoseRecordsDao
import com.ca.model.DateRange
import com.ca.model.GlucoseRecord
import com.ca.network.api.NetworkClient
import com.ca.records.domain.repository.GlucoseRecordsRepository
import com.ca.records.glucose.data.paging.GlucoseRecordsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GlucoseRecordsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val glucoseRecordsDao: GlucoseRecordsDao,
) : GlucoseRecordsRepository {

    override suspend fun records(cursor: String?, limit: Int): Flow<PagingData<GlucoseRecord>> {
        return Pager(
            config = PagingConfig(pageSize = limit),
            pagingSourceFactory = { GlucoseRecordsPagingSource(networkClient) }
        ).flow
    }

    override suspend fun recordsByRange(cursor: String?, range: DateRange, limit: Int): Flow<PagingData<GlucoseRecord>> {
        return Pager(
            config = PagingConfig(pageSize = limit),
            pagingSourceFactory = { GlucoseRecordsPagingSource(networkClient) }
        ).flow
    }
}