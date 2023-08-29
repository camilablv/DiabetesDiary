package com.ca.domain.model

import kotlinx.android.parcel.Parcelize
import java.time.LocalDate
import java.time.LocalTime

@Parcelize
data class GlucoseRecord(
    val cursor: String,
    val id: String,
    val level: Double,
    val note: String?,
    override val time: LocalTime,
    val date: LocalDate,
    val measuringMark: MeasuringMark
) : ListItem
