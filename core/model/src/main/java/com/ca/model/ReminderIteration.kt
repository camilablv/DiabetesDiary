package com.ca.model

enum class ReminderIteration(val text: String) {
    ONCE("Once"),
    DAILY("Daily")
}

val iterationOptions = listOf(
    ReminderIteration.ONCE,
    ReminderIteration.DAILY
)