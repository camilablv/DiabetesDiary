package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Grey100
import com.ca.designsystem.theme.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    expanded: Boolean,
    placeholder: @Composable () -> Unit,
    onDoneAction: (String) -> Unit,
    expandedMaxLines: Int,
    collapsedMaxLines: Int
) {
    val interactionSource = remember { MutableInteractionSource() }
    val noteText = remember { mutableStateOf(value) }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = Modifier
                .background(Grey100, Theme.shapes.large)
        ) {
            BasicTextField(
                value = value,
                onValueChange = {
                    noteText.value = it
                    onValueChange(it)
                },
                modifier = modifier
                    .fillMaxWidth(),
                textStyle = Theme.typography.bodyMedium,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onDoneAction(noteText.value)
                    }
                ),
                maxLines = if (expanded) expandedMaxLines else collapsedMaxLines
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

        Text(
            text = "${value.count()} from 140", //todo replace max character value to constant
            color = Color.Gray
        )
    }
}