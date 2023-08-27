package com.ca.alarmmanager.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

fun timeExactForAlarm(time: LocalTime, intervalDay: Int): Long {
    val today = LocalDate.now()
    val date = if (time > LocalTime.now()) today else today.plusDays(intervalDay.toLong())
    return ZonedDateTime
        .of(date, time, ZoneId.of(android.icu.util.TimeZone.getDefault().id))
        .toInstant()
        .toEpochMilli()
}