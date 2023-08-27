package com.ca.domain.model

enum class ReminderIteration(val text: String) {
    ONCE("Once"),
    DAILY("Daily");

    override fun toString(): String = text
}

val iterationOptions = listOf(
    ReminderIteration.ONCE,
    ReminderIteration.DAILY
)