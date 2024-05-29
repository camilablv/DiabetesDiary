package com.ca.home.presentation

import com.ca.model.ListItem
import java.time.LocalDate

data class HomeViewState(
    val listItems: List<ListItem> = listOf(),
    val selectedDate: LocalDate = LocalDate.now()
)
