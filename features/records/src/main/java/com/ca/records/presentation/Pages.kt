package com.ca.records.presentation

import com.ca.model.Page


sealed class RecordsPage(override val text: String) : Page {
    data object InsulinRecords : RecordsPage("Insulin")
    data object GlucoseRecords : RecordsPage("Glucose")
}

val pages = listOf(
    RecordsPage.InsulinRecords,
    RecordsPage.GlucoseRecords
)

