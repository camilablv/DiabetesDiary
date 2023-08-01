package com.ca.home.presentation

import com.ca.model.Reminder
import java.time.LocalDate

data class HomeViewState(
    val reminders: List<Reminder> = listOf(),
    val selectedDate: LocalDate = LocalDate.now()
)
