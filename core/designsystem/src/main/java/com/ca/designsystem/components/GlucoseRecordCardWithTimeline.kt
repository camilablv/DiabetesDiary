package com.ca.designsystem.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.common.utils.time
import com.ca.designsystem.components.glucosemeasuringmark.MeasuringMarkCard
import com.ca.designsystem.theme.Theme
import com.ca.model.GlucoseRecord


@Composable
fun GlucoseRecordCardWithTimeline(record: GlucoseRecord) {
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
                MeasuringMarkCard(mark = record.measuringMark)
                
                Row(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .weight(2f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = record.measuringMark.text,
                        style = Theme.typography.bodyLarge
                    )
                    Text(
                        text = record.level.toString(),
                        style = Theme.typography.bodyLarge
                    )
                }
            }

        }
    }
}