package com.ca.records.insulin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ca.network.api.NetworkClient
import com.ca.model.InsulinRecord
import com.ca.records.insulin.data.paging.InsulinRecordsPagingSource
import com.ca.records.domain.repository.RecordsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsulinRecordsRepository @Inject constructor(
    private val networkClient: NetworkClient
) : RecordsRepository {

    override suspend fun records(cursor: String?, limit: Int): Flow<PagingData<InsulinRecord>> {
        return Pager(
            config = PagingConfig(pageSize = limit),
            pagingSourceFactory = { InsulinRecordsPagingSource(networkClient) }
        ).flow
    }
}