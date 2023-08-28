package com.ca.designsystem.components.pickers

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.ca.designsystem.theme.Theme
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3DatePicker(
    expanded: Boolean,
    onDismiss: () -> Unit,
    date: LocalDate,
    setDate: (LocalDate) -> Unit
) {
    val state = rememberDatePickerState(
        initialDisplayedMonthMillis = toDateMillis(date),
        initialDisplayMode = DisplayMode.Picker
    )
    if (!expanded) return
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    setDate(toLocalDate(state.selectedDateMillis!!))
                    onDismiss()
                }
            ) {
                Text(
                    text = "Ok"
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Cancel"
                )
            }
        },
        shape = Theme.shapes.large,
        colors = DatePickerDefaults.colors(
            containerColor = Theme.colors.surface,
            selectedDayContainerColor = Theme.colors.secondary
        )
    ) {
        androidx.compose.material3.DatePicker(state = state)
    }
}

fun toDateMillis(date: LocalDate): Long {
    return date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
}

fun toLocalDate(date: Long): LocalDate {
    return Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDate()
}