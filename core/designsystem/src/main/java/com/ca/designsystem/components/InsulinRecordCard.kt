package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.InsulinRecord

@Composable
fun InsulinRecordCard(record: InsulinRecord) {
    Card(
        modifier = Modifier,
        elevation = Theme.elevations.default,
        shape = Theme.shapes.large
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(colorFromHex(record.insulin.color), CircleShape)
                )
                Text(
                    modifier = Modifier
                        .weight(2f),
                    text = record.time.toString(),
                    style = Theme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = record.insulin.name,
                    style = Theme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .weight(2f),
                    text = record.units.toString(),
                    style = Theme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }

            record.note?.let {
                Divider()
                Text(text = it)
            }
        }
    }
}