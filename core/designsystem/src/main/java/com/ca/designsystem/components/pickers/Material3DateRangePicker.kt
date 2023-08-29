package com.ca.designsystem.components.pickers

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3DateRangePicker(
    expanded: Boolean,
    onDismiss: () -> Unit,
    fromDate: LocalDate,
    toDate: LocalDate?,
    setRange: (from: LocalDate, to: LocalDate) -> Unit
) {
    if (!expanded) return

    val state = rememberDateRangePickerState(
        initialSelectedStartDateMillis = toDateMillis(fromDate),
        initialSelectedEndDateMillis = toDate?.let { toDateMillis(it) }
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            setRange(
                toLocalDate(state.selectedStartDateMillis!!),
                toLocalDate(state.selectedEndDateMillis!!)
            )
            onDismiss()
        }
    ) {
        androidx.compose.material3.DateRangePicker(
            modifier = Modifier
                .padding(16.dp),
            state = state
        )
    }
}