package com.ca.common.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDate.dayOfddPattern(): String = format(DateTimeFormatter.ofPattern("dd"))

fun LocalDate.monthOfMMMPattern(): String = format(DateTimeFormatter.ofPattern("MMM"))

fun LocalTime.timeOfhhmmPattern(): String = format(DateTimeFormatter.ofPattern("HH:mm"))

fun LocalDate.date(): String {
    return "${dayOfddPattern()} ${monthOfMMMPattern()}"
}

fun LocalDateTime.time(): String = format(DateTimeFormatter.ofPattern("hh:mm"))

fun LocalDate.dayName(): String =
    format(DateTimeFormatter.ofPattern("E"))

fun LocalDate.formatMonthYear(): String = format(DateTimeFormatter.ofPattern("MMMM y")).replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}

fun LocalDate.weekStartDate(weekStartDay: DayOfWeek = DayOfWeek.MONDAY): LocalDate {
    var date = this
    while (date.dayOfWeek != weekStartDay) {
        date = date.minusDays(1)
    }
    return date
}
fun LocalDate.getPrevDates(page: Int): List<LocalDate> {
    val startDAte = this.minusDays((page * 7).toLong())
    val dates = mutableListOf<LocalDate>()
    repeat(7) { day ->
        dates.add(startDAte.plusDays((day).toLong()))
    }
    return dates
}