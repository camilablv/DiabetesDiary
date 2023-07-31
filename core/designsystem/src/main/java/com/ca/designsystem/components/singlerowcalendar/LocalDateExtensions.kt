package com.ca.designsystem.components.singlerowcalendar

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream

fun LocalDate.getDay3LettersName(): String =
    format(DateTimeFormatter.ofPattern("EE"))

fun LocalDate.weekStartDate(weekStartDay: DayOfWeek = DayOfWeek.MONDAY): LocalDate {
    var date = this
    while (dayOfWeek != weekStartDay) {
        date = date.minusDays(1)
    }
    return date
}

fun LocalDate.startDates(): List<LocalDate> {
    val startDate = weekStartDate()
    val numOfDays = ChronoUnit.DAYS.between(startDate, startDate.plusDays(7))
    return Stream.iterate(startDate) { date ->
        date.plusDays(1)
    }
        .limit(numOfDays)
        .collect(Collectors.toList())
}

fun LocalDate.daysBetween(page: Int): List<LocalDate> {
    val startDate = weekStartDate().minusDays((page * 7).toLong())
    val numOfDays = ChronoUnit.DAYS.between(startDate, startDate.plusDays(7))
    return Stream.iterate(startDate) { date ->
        date.plusDays(1)
    }
        .limit(numOfDays)
        .collect(Collectors.toList())
}
