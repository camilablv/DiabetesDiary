package com.ca.records.presentation.pages

sealed class Page(val text: String) {
    object InsulinRecords : Page("Insulin")
    object GlucoseRecords : Page("Glucose")
}

val pages = listOf(
    Page.InsulinRecords,
    Page.GlucoseRecords
)

