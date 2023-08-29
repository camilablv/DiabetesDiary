package com.ca.designsystem.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.R
import com.ca.designsystem.theme.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun DateTimeCard(
    modifier: Modifier,
    text: String,
    icon: Painter,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(132.dp),
        onClick = onClick,
        shape = Theme.shapes.large,
        backgroundColor = Theme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = icon,
                contentDescription = "",
                tint = Theme.colors.onSurface
            )

            Text(
                modifier = Modifier,
                text = text,
                style = Theme.typography.bodyLarge,
                color = Theme.colors.onSurface
            )
        }
    }
}

@Composable
fun DateCard(
    modifier: Modifier,
    date: String,
    onClick: () -> Unit
) {
    DateTimeCard(
        modifier = modifier,
        text = date,
        icon = painterResource(id = R.drawable.calendar),
        onClick = onClick
    )
}

@Composable
fun TimeCard(
    modifier: Modifier,
    time: String,
    onClick: () -> Unit
) {
    DateTimeCard(
        modifier = modifier,
        text = time,
        icon = painterResource(id = R.drawable.schedule),
        onClick = onClick
    )
}