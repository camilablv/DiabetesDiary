package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ca.common.utils.colorFromHex
import com.ca.designsystem.theme.Theme

@Composable
fun InsulinEntry(
    name: String,
    color: String,
    defaultDosage: String
) {

    Card(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        shape = Theme.shapes.medium,
        elevation = Theme.elevations.default,
        backgroundColor = colorFromHex(color)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Black, CircleShape)
                    .background(color = colorFromHex(color))
            )

            Text(
                text = name,
                style = Theme.typography.bodyLarge
            )

            Text(
                text = defaultDosage,
                style = Theme.typography.bodyLarge
            )
        }
    }
}