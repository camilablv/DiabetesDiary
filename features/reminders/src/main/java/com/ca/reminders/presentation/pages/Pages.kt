package com.ca.reminders.presentation.pages

import com.ca.model.Page

sealed class RemindersPage(override val text: String) : Page {
    data object InsulinRecords : RemindersPage("Insulin")
    data object GlucoseRecords : RemindersPage("Glucose")
}

val pages = listOf(
    RemindersPage.InsulinRecords,
    RemindersPage.GlucoseRecords
)