package com.ca.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.commandiron.wheel_picker_compose.WheelTimePicker
import com.commandiron.wheel_picker_compose.core.TimeFormat
import java.time.LocalTime

@Composable
fun TimeWheelPicker(
    modifier: Modifier,
    time: LocalTime,
    onSnappedTime: (LocalTime) -> Unit
) {
    WheelTimePicker(
        modifier = modifier,
        startTime = time,
        onSnappedTime = { onSnappedTime(it) },
        timeFormat = TimeFormat.HOUR_24,
        size = DpSize(120.dp, 200.dp),
        textStyle = Theme.typography.headlineSmall
    )
}