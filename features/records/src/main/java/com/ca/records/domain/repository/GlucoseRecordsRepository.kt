package com.ca.records.domain.repository

import androidx.paging.PagingData
import com.ca.domain.model.GlucoseRecord
import kotlinx.coroutines.flow.Flow

interface GlucoseRecordsRepository {
    suspend fun records(cursor: String?, limit: Int): Flow<PagingData<GlucoseRecord>>
}