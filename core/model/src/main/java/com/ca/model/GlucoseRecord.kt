package com.ca.model

import java.time.LocalDateTime

data class GlucoseRecord(
    val id: String,
    val units: GlucoseUnits,
    val level: Double,
    val note: String,
    val dateTime: LocalDateTime
)
