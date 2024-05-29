package com.ca.records.glucose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ca.model.GlucoseRecord
import com.ca.network.api.NetworkClient
import com.ca.records.domain.repository.GlucoseRecordsRepository
import com.ca.records.glucose.data.paging.GlucoseRecordsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GlucoseRecordsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
) : GlucoseRecordsRepository {

    override suspend fun records(cursor: String?, limit: Int): Flow<PagingData<GlucoseRecord>> {
        return Pager(
            config = PagingConfig(pageSize = limit),
            pagingSourceFactory = { GlucoseRecordsPagingSource(networkClient) }
        ).flow
    }
}