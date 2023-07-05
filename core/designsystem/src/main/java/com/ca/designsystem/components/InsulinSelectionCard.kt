package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.DiaryTheme
import com.ca.designsystem.theme.Theme
import com.ca.model.Insulin

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
    Surface(
        modifier = modifier,
        elevation = Theme.elevations.default,
        shape = Theme.shapes.large
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = "Select Insulin",
                modifier = Modifier
                    .background(
                        Theme.colors.secondary,
                        RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    )
                    .fillMaxWidth()
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { onExpandedChange(it) }
            ) {
                TextField(
                    value = selectedInsulin?.name ?: "Select Insulin",
                    onValueChange = {},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    readOnly = true
                )

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