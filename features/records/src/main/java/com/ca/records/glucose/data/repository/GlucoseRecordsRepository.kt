package com.ca.records.glucose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ca.model.InsulinRecord
import com.ca.network.api.NetworkClient
import com.ca.records.domain.repository.RecordsRepository
import com.ca.records.glucose.data.paging.GlucoseRecordsPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GlucoseRecordsRepository@Inject constructor(
    private val networkClient: NetworkClient
) : RecordsRepository {

    override suspend fun records(cursor: String?, limit: Int): Flow<PagingData<InsulinRecord>> {
        return Pager(
            config = PagingConfig(pageSize = limit),
            pagingSourceFactory = { GlucoseRecordsPagingSource(networkClient) }
        ).flow
    }
}