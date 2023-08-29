package com.ca.records.presentation

import com.ca.domain.model.Page


sealed class RecordsPage(override val text: String) : Page {
    object InsulinRecords : RecordsPage("Insulin")
    object GlucoseRecords : RecordsPage("Glucose")
}

val pages = listOf(
    RecordsPage.InsulinRecords,
    RecordsPage.GlucoseRecords
)

