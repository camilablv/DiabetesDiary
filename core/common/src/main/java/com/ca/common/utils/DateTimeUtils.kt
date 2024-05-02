package com.ca.common.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDate.dayOfddPattern(): String = format(formatter("dd"))

fun LocalDate.monthOfMMMPattern(): String = format(formatter("MMM"))

fun LocalTime.timeOfHHmmPattern(): String = format(formatter("HH:mm"))

fun LocalDate.date(): String {
    return "${dayOfddPattern()} ${monthOfMMMPattern()}"
}

fun LocalDateTime.time(): String = format(formatter("hh:mm"))

fun LocalDate.dayName(locale: Locale): String =
    format(formatter("E", locale))

fun LocalDate.formatMonthYear(locale: Locale): String = format(formatter("MMMM y", locale)).replaceFirstChar {
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
fun LocalDate.getPrevDates(weeksFromCurrentDate: Int): List<LocalDate> {
    val beginningOfWeek = this.minusDays((weeksFromCurrentDate * 7).toLong())
    val dates = mutableListOf<LocalDate>()
    repeat(7) { day ->
        dates.add(beginningOfWeek.plusDays((day).toLong()))
    }
    return dates
}

private fun formatter(pattern: String, locale: Locale) = DateTimeFormatter.ofPattern(pattern, locale)

private fun formatter(pattern: String) = DateTimeFormatter.ofPattern(pattern)