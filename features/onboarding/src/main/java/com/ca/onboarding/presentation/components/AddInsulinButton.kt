package com.ca.onboarding.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ca.common.utils.toHex
import com.ca.designsystem.components.ColorPickerButton
import com.ca.designsystem.components.ScrollableCounter
import com.ca.data.model.Insulin
import com.vanpra.composematerialdialogs.color.ColorPalette
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun AddInsulinButton(
    add: (Insulin) -> Unit
) {
    var insulinName by remember { mutableStateOf("") }
    var defaultDosage by remember { mutableStateOf("") }
    var insulinColor by remember { mutableStateOf(ColorPalette.Primary[12]) }
    val dialogState = rememberMaterialDialogState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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
            ScrollableCounter()

            Button(
                onClick = {
                    val insulin = Insulin(
                        name = insulinName,
                        color = insulinColor.toHex(),
                        defaultDosage = 50
                    )
                    add(insulin)
                }
            ) {
                Text(text = "Add")
            }
        }
    }
}