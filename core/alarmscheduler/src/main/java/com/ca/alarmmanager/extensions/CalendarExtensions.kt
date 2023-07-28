package com.ca.alarmmanager.extensions

import java.time.LocalTime
import java.util.Calendar

fun Calendar.timeExactForAlarm(time: LocalTime): Calendar {
    timeInMillis = System.currentTimeMillis()

    set(Calendar.HOUR_OF_DAY, time.hour)
    set(Calendar.MINUTE, time.minute)

    return this
}