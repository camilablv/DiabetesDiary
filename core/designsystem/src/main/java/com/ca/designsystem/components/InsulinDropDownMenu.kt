package com.ca.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
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
    if (selectedInsulin == null) return

    val animatedFloat by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Card(
        modifier = modifier
            .fillMaxWidth(),
        onClick = { onExpandedChange(true) },
        shape = Theme.shapes.large,
        elevation = Theme.elevations.default
    ) {
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
                modifier = Modifier
                    .rotate(animatedFloat),
                painter = painterResource(R.drawable.round_arrow_down),
                contentDescription = ""
            )
        }

        MaterialTheme(
            shapes = MaterialTheme.shapes.copy(medium = RoundedCornerShape(12.dp))
        ) {
            DropdownMenu(
                modifier = Modifier,
                expanded = expanded,
                onDismissRequest = onDismiss,
                offset = DpOffset(0.dp, 4.dp)
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
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddInsulinButton(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        shape = Theme.shapes.large,
        elevation = Theme.elevations.default
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .wrapContentHeight(align = Alignment.CenterVertically),
            text = "Add Insulin",
            style = Theme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Gray,
        )
    }
}