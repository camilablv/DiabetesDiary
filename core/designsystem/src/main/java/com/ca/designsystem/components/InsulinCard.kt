package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.Insulin

@Composable
fun InsulinCard(
    modifier: Modifier,
    insulin: Insulin
) {
    Card(
        modifier = modifier
            .height(72.dp)
            .fillMaxWidth(),
        shape = Theme.shapes.large,
        elevation = Theme.elevations.default,
        backgroundColor = Theme.colors.surface
    ) {

        Column {
            Box(
                modifier = Modifier
                    .background(colorFromHex(insulin.color), RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .fillMaxWidth()
                    .weight(2f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = insulin.name,
                    style = Theme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(12f)
                )

                Text(
                    modifier = Modifier
                        .weight(2f),
                    text = "${insulin.defaultDose} un",
                    style = Theme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}