package com.ca.onboarding.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.ScrollableCounter
import com.ca.settings.domain.model.Insulin

@Composable
fun AddInsulinItem(
    add: (Insulin) -> Unit
) {
    var insulinName by remember { mutableStateOf("") }
    var defaultDosage by remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Yellow, CircleShape)
                .size(40.dp)
        )

        OutlinedTextField(
            value = insulinName,
            onValueChange = {
                insulinName = it
            },
            modifier = Modifier
                .height(48.dp)
                .width(100.dp)
        )

//        OutlinedTextField(
//            value = defaultDosage,
//            onValueChange = {
//                defaultDosage = it
//            },
//            modifier = Modifier
//                .height(48.dp)
//                .width(100.dp),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
//        )

        ScrollableCounter(count = 10)

    }
}