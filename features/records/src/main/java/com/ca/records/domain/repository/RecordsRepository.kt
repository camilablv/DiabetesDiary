package com.ca.records.domain.repository

import com.ca.model.InsulinRecord

interface RecordsRepository {
    suspend fun insulinRecords(cursor: String, limit: Int): List<InsulinRecord?>?
}