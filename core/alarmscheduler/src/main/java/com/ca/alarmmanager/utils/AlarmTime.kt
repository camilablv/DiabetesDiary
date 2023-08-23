package com.ca.alarmmanager.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

fun timeExactForAlarm(time: LocalTime): Long {
    val date = if (time > LocalTime.now()) LocalDate.now() else LocalDate.now().plusDays(1)
    return ZonedDateTime
        .of(date, time, ZoneId.of(android.icu.util.TimeZone.getDefault().id))
        .toInstant()
        .toEpochMilli()
}