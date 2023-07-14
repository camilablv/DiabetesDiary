package com.ca.model

import java.time.LocalDateTime

data class InsulinRecord(
    val cursor: String,
    val id: String,
    val insulin: Insulin,
    val dateTime: LocalDateTime,
    val units: Double,
    val note: String?
)
