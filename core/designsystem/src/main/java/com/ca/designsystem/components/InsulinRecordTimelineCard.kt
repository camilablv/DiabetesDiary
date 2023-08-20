package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ca.common.utils.timeOfHHmmPattern
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.InsulinRecord

@Composable
fun InsulinRecordTimelineCard(
    record: InsulinRecord,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = record.time.timeOfHHmmPattern(),
            style = Theme.typography.bodyLarge
        )

        Card(
            modifier = Modifier,
            shape = Theme.shapes.large,
            elevation = Theme.elevations.default,
            backgroundColor = Theme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
            ) {
                Text(
                    text = "Insulin",
                    style = Theme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .height(56.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
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

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = record.units.toInt().toString(),
                                style = Theme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "IU",
                                style = Theme.typography.bodySmall,
                            )
                        }

                    }
                }
                
                record.note?.let { 
                    Text(text = it)
                }
            }
        }
    }
}