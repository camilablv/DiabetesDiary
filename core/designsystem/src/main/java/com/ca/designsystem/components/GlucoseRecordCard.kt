package com.ca.designsystem.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.common.utils.timeOfHHmmPattern
import com.ca.designsystem.theme.Theme
import com.ca.domain.model.GlucoseRecord

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GlucoseRecordCard(
    record: GlucoseRecord
) {
    Card(
        onClick = {},
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
//                Icon(painter = painterResource(id = record.measuringMark.icon), contentDescription = "")
                Text(
                    modifier = Modifier
                        .weight(2f),
                    text = record.time.timeOfHHmmPattern(),
                    style = Theme.typography.bodyMedium
                )
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = record.level.toString(),
                    style = Theme.typography.bodyMedium
                )
            }

            record.note?.let {
                Divider()
                Text(text = it)
            }
        }
    }
}