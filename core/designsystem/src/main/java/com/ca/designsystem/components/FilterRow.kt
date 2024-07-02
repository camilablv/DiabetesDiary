package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import java.util.Date

enum class FilterItem(val text: String, val range: Date) {
    ALL("All", Date()),
    TODAY("Today", Date()),
    WEEK("Week", Date()),
    MONTH("Month", Date()),
    YEAR("Year", Date()),
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterRow(
    selectedFilter: FilterItem,
    filter: (FilterItem) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilterItem.entries.forEach {
            DateFilterItem(
                text = it.text,
                selected = it == selectedFilter
            ) {
                filter(it)
            }
        }
    }
}

@Composable
private fun DateFilterItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = if (selected) Theme.colors.onSecondary else Theme.colors.onBackground,
        modifier = Modifier
            .clickable { onClick() }
            .widthIn(min = 42.dp)
            .background(if (selected) Theme.colors.secondary else Theme.colors.background, Theme.shapes.round)
            .border(1.dp, Color.Gray, Theme.shapes.round)
            .padding(8.dp),
        textAlign = TextAlign.Center

    )
}