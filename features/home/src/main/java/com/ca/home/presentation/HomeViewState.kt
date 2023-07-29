package com.ca.home.presentation

import com.ca.model.Reminder

data class HomeViewState(
    val reminders: List<Reminder> = listOf()
)
