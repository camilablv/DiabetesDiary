package com.ca.records.glucose.presentation

import com.ca.designsystem.components.FilterItem

data class GlucoseRecordsViewState(
    val selectedDateFilter: FilterItem = FilterItem.ALL
)