package com.ca.alarmmanager

import java.time.LocalTime

interface AlarmScheduler {
    fun scheduleOnce(time: LocalTime)
    fun scheduleDaily(time: LocalTime)
    fun cancel()
}