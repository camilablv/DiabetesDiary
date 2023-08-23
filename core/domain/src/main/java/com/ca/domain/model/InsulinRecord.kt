package com.ca.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class InsulinRecord(
    val cursor: String,
    val id: String,
    val insulin: Insulin,
    override val time: LocalTime,
    val date: LocalDate,
    val units: Double,
    val note: String?
) : ListItem
