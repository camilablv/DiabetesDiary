package com.ca.recordinsulin.domain.repository

import com.ca.model.Insulin

interface RecordInsulinRepository {
    suspend fun insulins(): List<Insulin>
}