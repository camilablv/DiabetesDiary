package com.ca.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReminderCard(
    backgroundColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xffa18cd1), Color(0xfffbc2eb)),
        startX = 0.0f,
        endX = 1000.0f,
        tileMode = TileMode.Repeated
    )

    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = Theme.shapes.large,
        elevation = Theme.elevations.default,
        border = BorderStroke(4.dp, gradient),
        backgroundColor = backgroundColor
    ) {
        content()
    }
}