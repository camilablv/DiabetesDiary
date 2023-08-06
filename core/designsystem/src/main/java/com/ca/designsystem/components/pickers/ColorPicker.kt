package com.ca.designsystem.components.pickers

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.DialogProperties
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.color.ColorPalette
import com.vanpra.composematerialdialogs.color.colorChooser

@Composable
fun ColorPicker(
    color: Color,
    state: MaterialDialogState,
    select: (Color) -> Unit
) {

    var selectedColor: Color = color

    MaterialDialog(
        dialogState = state,
        buttons = {
            positiveButton(
                text = "Select",
                onClick = { select.invoke(selectedColor) }
            )
            negativeButton(
                text = "Cancel",
                onClick = { state.hide() }
            )
        },
        properties = DialogProperties()
    ) {
        colorChooser(
            colors = ColorPalette.Primary,
            initialSelection = ColorPalette.Primary.indexOf(color),
        ) {
            selectedColor = it
        }
    }
}