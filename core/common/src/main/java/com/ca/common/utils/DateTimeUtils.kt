package com.ca.common.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.Stream

fun LocalDate.dayOfddPattern(): String = format(DateTimeFormatter.ofPattern("dd"))

fun LocalDate.monthOfMMMPattern(): String = format(DateTimeFormatter.ofPattern("MMM"))

fun LocalTime.timeOfhhmmPattern(): String = format(DateTimeFormatter.ofPattern("HH:mm"))

fun LocalDate.date(): String {
    return "${dayOfddPattern()} ${monthOfMMMPattern()}"
}

fun LocalDateTime.time(): String = format(DateTimeFormatter.ofPattern("hh:mm"))

fun LocalDate.getDay3LettersName(): String =
    format(DateTimeFormatter.ofPattern("EE"))


fun LocalDate.weekStartDate(weekStartDay: DayOfWeek = DayOfWeek.MONDAY): LocalDate {
    var date = this
    while (date.dayOfWeek != weekStartDay) {
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

fun LocalDate.prevWeek(fromDate: LocalDate): List<LocalDate> {
    val startDate = fromDate.minusDays(7)
    val numOfDays = ChronoUnit.DAYS.between(startDate, startDate.plusDays(7))
    return Stream.iterate(fromDate) { date -> date.plusDays(1) }
        .limit(numOfDays)
        .collect(Collectors.toList())
}

fun LocalDate.getNextDates(count: Int): List<LocalDate> {
    val dates = mutableListOf<LocalDate>()
    repeat(count) { day ->
        dates.add(this.plusDays((day).toLong()))
    }
    return dates
}

fun LocalDate.getPrevDates(page: Int): List<LocalDate> {
    val startDAte = this.minusDays((page * 7).toLong())
    val dates = mutableListOf<LocalDate>()
    repeat(7) { day ->
        dates.add(startDAte.plusDays((day).toLong()))
    }
    return dates
}