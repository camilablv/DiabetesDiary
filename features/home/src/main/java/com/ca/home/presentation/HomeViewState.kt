package com.ca.home.presentation

import com.ca.model.Record
import com.ca.model.Reminder
import java.time.LocalDate

data class HomeViewState(
    val reminders: List<Reminder> = listOf(),
    val records: List<Record> = listOf(),
    val selectedDate: LocalDate = LocalDate.now()
)
