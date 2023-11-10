package com.ca.designsystem.components.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ca.designsystem.components.Counter
import com.ca.designsystem.components.DiaryTextField
import com.ca.designsystem.components.pickers.DiaryColorPicker
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.designsystem.utils.toHex
import com.ca.model.Insulin
import com.vanpra.composematerialdialogs.color.ColorPalette

@Composable
fun EditInsulinDialog(
    show: Boolean,
    edit: (id: String?, name: String, color: String, dose: Int) -> Unit,
    onDismiss: () -> Unit,
    editableInsulin: Insulin? = null
) {
    if (!show) return
    Dialog(
        onDismissRequest = onDismiss
    ) {
        var insulinName by remember { mutableStateOf(editableInsulin?.name ?: "") }
        var defaultDosage by remember { mutableStateOf(editableInsulin?.defaultDose ?: 0) }
        var insulinColor by remember {
            mutableStateOf(
                if (editableInsulin?.color != null) colorFromHex(editableInsulin.color)
                else ColorPalette.Primary[12]
            )
        }
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

                DiaryTextField(
                    value = insulinName,
                    onValueChange = {
                        insulinName = it
                    },
                    modifier = Modifier,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    placeholder = { Text(text = "Type insulin name..") },
                    onDoneAction = {

                    }
                )

                DiaryColorPicker(
                    selectedColor = insulinColor,
                    select = { insulinColor = it }
                )

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
                            edit(editableInsulin?.id, insulinName, insulinColor.toHex(), defaultDosage)
                            onDismiss()
                        },
                        shape = Theme.shapes.large,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Theme.colors.secondary
                        )
                    ) {
                        Text(
                            text = if (editableInsulin == null) "Add" else "Edit",
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
fun EditInsulinDialogPreview() {
    val lambda: (String?, String, String, Int) -> Unit = { _, _, _, _ -> }
    DiaryTheme {
        EditInsulinDialog(
            show = true,
            edit = { p1, p2, p3, p4 ->
                lambda(p1, p2, p3, p4)
            },
            onDismiss = {}
        )
    }
}