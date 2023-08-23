package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme
import com.ca.domain.model.Insulin

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InsulinSelectionCard(
    modifier: Modifier,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onSelect: (Insulin) -> Unit,
    onDismiss: () -> Unit,
    selectedInsulin: Insulin?,
    options: List<Insulin>
) {
    CardWithTitle(
        modifier = modifier,
        title = "Select Insulin"
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { onExpandedChange(it) }
        ) {
            BasicTextField(
                value = selectedInsulin?.name ?: "Select Insulin",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(),
                readOnly = true,
                textStyle = Theme.typography.bodyLarge
            ) {
                TextFieldDefaults.TextFieldDecorationBox(
                    value = selectedInsulin?.name ?: "Select Insulin",
                    innerTextField = it,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = MutableInteractionSource(),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black
                    )
                )
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { onDismiss() }
            ) {
                options.forEach {
                    DropdownMenuItem(onClick = { onSelect(it) }) {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun InsulinSelectionCardPreview() {

    val options = listOf(
        Insulin("", name = "Insulin 1", "", 0),
        Insulin("", name = "Insulin 2", "", 0),
        Insulin("", name = "Insulin 3", "", 0)
    )

    val expanded = remember { mutableStateOf(false) }

    DiaryTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.colors.background)
        ) {
            InsulinSelectionCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                expanded = expanded.value,
                onExpandedChange = { expanded.value = !expanded.value },
                onSelect = {},
                onDismiss = { expanded.value = false },
                selectedInsulin = options[0],
                options = options
            )
        }
    }
}