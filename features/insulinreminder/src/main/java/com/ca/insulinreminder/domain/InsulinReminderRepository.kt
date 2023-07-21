package com.ca.insulinreminder.domain

import java.time.LocalTime

interface InsulinReminderRepository {
    fun scheduleOnce(time: LocalTime)
}