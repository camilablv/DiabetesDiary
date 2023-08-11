package com.ca.designsystem.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ca.designsystem.components.ColorPickerButton
import com.ca.designsystem.components.Counter
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.toHex
import com.vanpra.composematerialdialogs.color.ColorPalette

@Composable
fun AddInsulinDialog(
    show: Boolean,
    add: (name: String, color: String, dose: Int) -> Unit,
    onDismiss: () -> Unit
) {
    if (!show) return
    Dialog(
        onDismissRequest = onDismiss
    ) {

        var insulinName by remember { mutableStateOf("") }
        var defaultDosage by remember { mutableStateOf(0) }
        var insulinColor by remember { mutableStateOf(ColorPalette.Primary[12]) }
        Surface(
            shape = Theme.shapes.large,
            color = Theme.colors.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Add Insulin",
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.onSurface
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ColorPickerButton(
                        color = insulinColor,
                        modifier = Modifier
                            .size(48.dp),
                        select = {
                            insulinColor = it
                        }
                    )

                    OutlinedTextField(
                        value = insulinName,
                        onValueChange = {
                            insulinName = it
                        },
                        modifier = Modifier
                            .height(48.dp)
                            .background(Color.White, Theme.shapes.large),
                        shape = Theme.shapes.large
                    )
                }

//                NumberWheelPicker(
//                    modifier = Modifier,
//                    startItem = 5,
//                    count = 100,
//                    visibleItemsCount = 3,
//                    size = DpSize(128.dp, 128.dp)
//                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Counter(
                        value = defaultDosage,
                        increment = { defaultDosage++ },
                        decrement = { defaultDosage-- },
                        modifier = Modifier,
                        onValueChanged = { defaultDosage = it.toInt() }
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {


                    TextButton(onClick = onDismiss) {
                        Text(
                            text = "Cancel",
                            color = Theme.colors.secondary
                        )
                    }

                    Button(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        onClick = {
                            add(insulinName, insulinColor.toHex(), defaultDosage)
                            onDismiss()
                        },
                        shape = Theme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Theme.colors.secondary
                        )
                    ) {
                        Text(
                            text = "Add",
                            color = Theme.colors.onSecondary
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AddInsulinDialog() {
    val lambda: (String, String, Int) -> Unit = {p1, p2, p3 ->}
    DiaryTheme {
        AddInsulinDialog(
            show = true,
            add = { p1, p2, p3 ->
                lambda(p1, p2, p3)
            }
        ) {}
    }
}