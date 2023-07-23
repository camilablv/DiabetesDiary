package com.ca.alarmmanager

import java.time.LocalTime

interface AlarmSchedule {
    fun scheduleOnce(time: LocalTime)
    fun scheduleDaily(time: LocalTime)
    fun cancel()
}