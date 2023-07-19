package com.ca.common.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalDate.dayOfddPattern(): String = format(DateTimeFormatter.ofPattern("dd"))

fun LocalDate.monthOfMMMPattern(): String = format(DateTimeFormatter.ofPattern("MMM"))

fun LocalTime.timeOfhhmmPattern(): String = format(DateTimeFormatter.ofPattern("HH:mm"))

fun LocalDate.date(): String {
    return "${dayOfddPattern()} ${monthOfMMMPattern()}"
}

fun LocalDateTime.time(): String = format(DateTimeFormatter.ofPattern("hh:mm"))