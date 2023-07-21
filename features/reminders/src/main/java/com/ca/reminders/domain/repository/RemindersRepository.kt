package com.ca.reminders.domain.repository

import java.time.LocalTime

interface RemindersRepository {
    fun scheduleOnce(time: LocalTime)
}