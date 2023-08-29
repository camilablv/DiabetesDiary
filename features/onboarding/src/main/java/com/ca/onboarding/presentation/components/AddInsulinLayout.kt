package com.ca.onboarding.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.Counter
import com.ca.designsystem.components.DiaryTextField
import com.ca.designsystem.components.pickers.DiaryColorPicker
import com.ca.designsystem.utils.toHex
import com.vanpra.composematerialdialogs.color.ColorPalette

@Composable
fun AddInsulinLayout(
    add: (name: String, color: String, dose: Int) -> Unit
) {
    var insulinName by remember { mutableStateOf("") }
    var defaultDosage by remember { mutableStateOf(0) }
    var insulinColor by remember { mutableStateOf(ColorPalette.Primary[12]) }

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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
                select = {
                    insulinColor = it
                }
            )

            Counter(
                value = defaultDosage,
                increment = { defaultDosage++ },
                decrement = { defaultDosage-- },
                modifier = Modifier,
                onValueChanged = { defaultDosage = it.toInt() }
            )

            Button(
                onClick = {
                    add(insulinName, insulinColor.toHex(), defaultDosage)
                }
            ) {
                Text(text = "Add")
            }
        }
    }
}