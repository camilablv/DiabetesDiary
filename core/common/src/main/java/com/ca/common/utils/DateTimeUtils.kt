package com.ca.common.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalDate.currentDay(): String = format(DateTimeFormatter.ofPattern("dd"))

fun LocalDate.currentMonth(): String = format(DateTimeFormatter.ofPattern("MMM"))

fun LocalTime.currentTime(): String = format(DateTimeFormatter.ofPattern("hh:mm"))

fun LocalDate.currentDate(): String {
    return "${currentDay()} ${currentMonth()}"
}