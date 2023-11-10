package com.ca.reminders.presentation.pages

import com.ca.model.Page

sealed class RemindersPage(override val text: String) : Page {
    object InsulinRecords : RemindersPage("Insulin")
    object GlucoseRecords : RemindersPage("Glucose")
}

val pages = listOf(
    RemindersPage.InsulinRecords,
    RemindersPage.GlucoseRecords
)