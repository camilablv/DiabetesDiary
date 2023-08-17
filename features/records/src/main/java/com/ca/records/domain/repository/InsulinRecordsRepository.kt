package com.ca.records.domain.repository

import androidx.paging.PagingData
import com.ca.model.InsulinRecord
import kotlinx.coroutines.flow.Flow

interface InsulinRecordsRepository {
    suspend fun records(cursor: String?, limit: Int): Flow<PagingData<InsulinRecord>>
}