package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.common.utils.time
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.InsulinRecord

@Composable
fun InsulinRecordCardWithTimeline(
    record: InsulinRecord
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = record.dateTime.time(),
            style = Theme.typography.bodyLarge
        )

        Card(
            modifier = Modifier,
            shape = Theme.shapes.large,
            elevation = Theme.elevations.default
        ) {
            Row(
                modifier = Modifier
                    .height(56.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(start = 8.dp)
                        .background(colorFromHex(record.insulin.color), Theme.shapes.large)
                        .aspectRatio(1f)
                )

                Row(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .weight(2f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = record.insulin.name,
                        style = Theme.typography.bodyLarge
                    )
                    Text(
                        text = record.units.toInt().toString(),
                        style = Theme.typography.bodyLarge
                    )
                }
            }

        }
    }
}