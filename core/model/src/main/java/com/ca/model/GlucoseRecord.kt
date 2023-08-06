package com.ca.model

import java.time.LocalDate
import java.time.LocalTime

data class GlucoseRecord(
    val id: String,
    val level: Double,
    val note: String?,
    override val time: LocalTime,
    val date: LocalDate,
    val measuringMark: MeasuringMark
) : ListItem
