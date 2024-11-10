package com.ca.records.glucose.presentation

import com.ca.model.DateFilterItem

data class GlucoseRecordsViewState(
    val selectedDateFilter: DateFilterItem = DateFilterItem.ALL
)