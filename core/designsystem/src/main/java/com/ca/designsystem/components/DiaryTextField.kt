package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import com.ca.designsystem.theme.Grey100
import com.ca.designsystem.theme.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DiaryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    placeholder: @Composable () -> Unit,
    onDoneAction: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    maxLines: Int = 1
) {
    val interactionSource = remember { MutableInteractionSource() }
    val noteText = remember { mutableStateOf(value) }

    Box(
        modifier = modifier
            .background(Grey100, Theme.shapes.large)
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                noteText.value = it
                onValueChange(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = Theme.typography.bodyMedium,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    onDoneAction(noteText.value)
                }
            ),
            maxLines = maxLines
        ) { textField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = textField,
                enabled = true,
                singleLine = false,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.Black
                ),
                placeholder = placeholder
            )
        }
    }
}