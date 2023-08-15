package com.ca.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
fun BottomSheetContainer(
    content: @Composable (() -> Unit)
) {
    Box(
        modifier = Modifier
            .background(
                Theme.colors.surface,
                Theme.shapes.bottomSheet
            )
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Canvas(
                modifier = Modifier
            ) {
                drawRoundRect(
                    color = Color.Gray,
                    cornerRadius = CornerRadius(60f, 60f),
                    style = Stroke(width = 15f, cap = StrokeCap.Round),
                    size = Size(width = 28.dp.toPx(), height = 1.dp.toPx()),
                    alpha = 0.3f
                )
            }
            content()
        }
    }
}

@Composable
fun BottomSheetMenuOption(
    icon: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "")
        Text(text = title)
    }
}