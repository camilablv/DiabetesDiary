package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.domain.model.Insulin

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InsulinDropDownMenu(
    modifier: Modifier,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onSelect: (Insulin) -> Unit,
    onDismiss: () -> Unit,
    selectedInsulin: Insulin?,
    options: List<Insulin>
) {
    Card(
        modifier = modifier,
        onClick = { onExpandedChange(true) },
        shape = Theme.shapes.small,
        elevation = Theme.elevations.default
    ) {
        if (selectedInsulin == null) {
            Text(text = "Add Insulin")
        } else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(colorFromHex(selectedInsulin.color), Theme.shapes.large)
                        .aspectRatio(1f)
                )
                Text(
                    modifier = Modifier
                        .weight(7f)
                        .padding(start = 8.dp),
                    text = selectedInsulin.name,
                    style = Theme.typography.bodyLarge
                )
                Icon(
                    painter =
                        if (expanded) painterResource(id = R.drawable.round_arrow_up)
                        else painterResource(R.drawable.round_arrow_down),
                    contentDescription = ""
                )
            }

        }

        DropdownMenu(
            modifier = Modifier.width(344.dp),
            expanded = expanded,
            onDismissRequest = onDismiss
        ) {
            options.forEach { 
                DropdownMenuItem(
                    modifier = Modifier,
                    onClick = { onSelect(it) },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(colorFromHex(it.color), Theme.shapes.large)
                                .aspectRatio(1f)
                        )
                        Text(
                            modifier = Modifier
                                .weight(7f)
                                .padding(start = 8.dp),
                            text = it.name,
                            style = Theme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
    
}