package com.ca.records.insulin.presentation

import com.ca.model.DateFilterItem

data class InsulinRecordsViewState(
    val selectedDateFilter: DateFilterItem = DateFilterItem.ALL
)
