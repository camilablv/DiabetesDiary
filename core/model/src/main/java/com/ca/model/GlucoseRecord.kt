package com.ca.model

import java.time.LocalDateTime

data class GlucoseRecord(
    val id: String,
    val level: Double,
    val note: String?,
    override val dateTime: LocalDateTime,
    val measuringMark: MeasuringMark
) : Record
