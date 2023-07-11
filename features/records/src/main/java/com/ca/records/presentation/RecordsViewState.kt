package com.ca.records.presentation

import com.ca.model.GlucoseRecord
import com.ca.model.InsulinRecord

data class RecordsViewState(
    val insulinRecords: List<InsulinRecord?> = emptyList(),
    val glucoseRecords: List<GlucoseRecord> = emptyList()
)
