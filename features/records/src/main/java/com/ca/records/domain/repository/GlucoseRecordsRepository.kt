package com.ca.records.domain.repository

import androidx.paging.PagingData
import com.ca.model.DateRange
import com.ca.model.GlucoseRecord
import kotlinx.coroutines.flow.Flow

interface GlucoseRecordsRepository {
    suspend fun records(cursor: String?, limit: Int): Flow<PagingData<GlucoseRecord>>

    suspend fun recordsByRange(cursor: String?, range: DateRange,  limit: Int): Flow<PagingData<GlucoseRecord>>
}