package com.ca.designsystem.components.pickers

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme
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

@Composable
fun DiaryColorPicker(
    selectedColor: Color,
    select: (Color) -> Unit
) {
    val borderColor = Theme.colors.onSurface

    Box(modifier = Modifier) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth(),
            columns = GridCells.Fixed(8),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(colors.size) {
                Canvas(
                    modifier = Modifier
                        .size(36.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) { select(colors[it]) }
                ) {
                    drawCircle(
                        color = colors[it],
                        radius = 16.dp.toPx()
                    )
                    if (colors[it] == selectedColor) {
                        drawCircle(
                            color = borderColor,
                            radius = 16.dp.toPx(),
                            style = Stroke(2.dp.toPx())
                        )
                    }
                }
            }
        }
    }
}

private val colors = listOf(
    Color(0xFFF44336),  Color(0xFF9C27B0), Color(0xFF673AB7),
    Color(0xFF3F51B5), Color(0xFF2196F3), Color(0xFF03A9F4),
    Color(0xFF00BCD4), Color(0xFF009688), Color(0xFF4CAF50),
    Color(0xFF8BC34A), Color(0xFFCDDC39), Color(0xFFFFEB3B),
    Color(0xFFFFC107), Color(0xFFFF9800), Color(0xFFFF5722),
    Color(0xFF607D8B)
)

@Composable
@Preview
fun ColorPickerPreview() {
    val selectedColor = remember {
        mutableStateOf(Color(0xFFFFEB3B))
    }

    DiaryTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            DiaryColorPicker(
                selectedColor = selectedColor.value,
                select = {
                    selectedColor.value = it
                }
            )
        }
    }
}