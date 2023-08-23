package com.ca.records.insulin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ca.domain.model.InsulinRecord
import com.ca.network.api.NetworkClient
import com.ca.records.insulin.data.paging.InsulinRecordsPagingSource
import com.ca.records.domain.repository.InsulinRecordsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsulinRecordsRepositoryImpl @Inject constructor(
    private val networkClient: NetworkClient
) : InsulinRecordsRepository {

    override suspend fun records(cursor: String?, limit: Int): Flow<PagingData<InsulinRecord>> {
        return Pager(
            config = PagingConfig(pageSize = limit),
            pagingSourceFactory = { InsulinRecordsPagingSource(networkClient) }
        ).flow
    }
}