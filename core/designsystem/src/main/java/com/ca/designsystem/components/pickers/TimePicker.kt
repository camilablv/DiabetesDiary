package com.ca.designsystem.components.pickers

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.marosseleng.compose.material3.datetimepickers.time.ui.dialog.TimePickerDialog
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePicker(
    expanded: Boolean,
    onDismiss: () -> Unit,
    time: LocalTime,
    setTime: (LocalTime) -> Unit
) {
    if (!expanded) return
    TimePickerDialog(
        onDismissRequest = onDismiss,
        onTimeChange = {
            setTime(it)
            onDismiss()
        },
        initialTime = time
    )
}