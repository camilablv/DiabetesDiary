package com.ca.designsystem.components.pickers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3TimePicker(
    expanded: Boolean,
    onDismiss: () -> Unit,
    time: LocalTime,
    setTime: (LocalTime) -> Unit
) {
    if (!expanded) return

    val state = rememberTimePickerState(
        initialHour = time.hour,
        initialMinute = time.minute,
        is24Hour = true
    )

    AlertDialog(
        modifier = Modifier
            .background(Theme.colors.surface, Theme.shapes.large),
        onDismissRequest = onDismiss
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            androidx.compose.material3.TimePicker(
                modifier = Modifier
                    .padding(16.dp),
                state = state
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = {
                        onDismiss()
                    }
                ) {
                    Text(
                        text = "Cancel"
                    )
                }

                TextButton(
                    onClick = {
                        setTime(LocalTime.of(state.hour, state.minute))
                        onDismiss()
                    }
                ) {
                    Text(
                        text = "Ok"
                    )
                }
            }
        }

    }
}