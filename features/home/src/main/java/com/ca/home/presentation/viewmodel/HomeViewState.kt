package com.ca.home.presentation.viewmodel

import com.ca.model.Record
import com.ca.model.Reminder
import java.time.LocalDate

data class HomeViewState(
    val recordsByDate: List<Record> = listOf(),
    val reminders: List<Reminder> = listOf(),
    val selectedDate: LocalDate = LocalDate.now()
)
