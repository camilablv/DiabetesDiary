package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.pickers.ColorPicker
import com.ca.designsystem.theme.Theme
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun ColorPickerButton(
    color: Color,
    modifier: Modifier,
    select: (Color) -> Unit
) {
    val pickerState = rememberMaterialDialogState()

    ColorPicker(
        color = color,
        state = pickerState
    ) {
        select.invoke(it)
    }

    Box(
        modifier = modifier
            .background(color, Theme.shapes.large)
            .border(1.dp, Color.Black, Theme.shapes.large)
            .clickable {
                pickerState.show()
            }
    )
}