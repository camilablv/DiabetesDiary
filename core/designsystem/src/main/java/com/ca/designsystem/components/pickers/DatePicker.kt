package com.ca.designsystem.components.pickers

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import java.time.LocalDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DatePicker(
    expanded: Boolean,
    onDismiss: () -> Unit,
    setDate: (LocalDate) -> Unit
) {
    if (!expanded) return
    DatePickerDialog(
        onDismissRequest = onDismiss,
        onDateChange = {
            setDate(it)
            onDismiss()
        },
        initialDate = LocalDate.now()
    )
}