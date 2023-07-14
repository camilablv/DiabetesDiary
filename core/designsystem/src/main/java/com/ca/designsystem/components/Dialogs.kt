package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
            shape = Theme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Add Insulin",
                    style = Theme.typography.headlineSmall
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
                            .background(Color.White, CircleShape),
                        shape = CircleShape
                    )


                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
//                    Counter(
//                        value = defaultDosage,
//                        increment = { defaultDosage++ },
//                        decrement = { defaultDosage-- },
//                        modifier = Modifier,
//                        onValueChanged = { defaultDosage = it.toInt() }
//                    )

                    Button(
                        onClick = {
                            add(insulinName, insulinColor.toHex(), defaultDosage)
                            onDismiss()
                        }
                    ) {
                        Text(text = "Add")
                    }
                }
            }
        }
    }
}