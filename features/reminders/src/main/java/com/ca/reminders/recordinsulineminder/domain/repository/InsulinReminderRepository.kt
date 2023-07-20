package com.ca.reminders.recordinsulineminder.domain.repository

import java.time.LocalTime

interface InsulinReminderRepository {
    fun schedule(time: LocalTime)
}