package com.ca.onboarding.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.ColorPickerButton
import com.ca.designsystem.components.ScrollableCounter
import com.ca.settings.domain.model.Insulin
import com.vanpra.composematerialdialogs.color.ColorPalette
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun AddInsulinItem(
    add: (Insulin) -> Unit
) {
    var insulinName by remember { mutableStateOf("") }
    var defaultDosage by remember { mutableStateOf("") }
    var insulinColor by remember { mutableStateOf(ColorPalette.Primary[12]) }
    val dialogState = rememberMaterialDialogState()



    Column {
        OutlinedTextField(
            value = insulinName,
            onValueChange = {
                insulinName = it
            },
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
        )



        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ColorPickerButton(
                color = insulinColor,
                modifier = Modifier
                    .padding(8.dp),
                select = {
                    insulinColor = it
                }
            )

            ScrollableCounter()
        }
    }


}