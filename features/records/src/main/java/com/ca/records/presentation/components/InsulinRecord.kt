package com.ca.records.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.common.utils.time
import com.ca.designsystem.theme.Theme
import com.ca.designsystem.utils.colorFromHex
import com.ca.model.InsulinRecord

@Composable
fun InsulinRecord(record: InsulinRecord) {
    Card(
        modifier = Modifier,
        elevation = Theme.elevations.default,
        shape = Theme.shapes.large
    ) {
        Column(

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(
                        colorFromHex(record.insulin.color),
                        RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    )
            )
            Row(

            ) {
                Text(text = record.dateTime.time())
                Text(text = record.insulin.name)
                Text(text = record.units.toString())
            }

            record.note?.let { Text(text = it) }
        }
    }
}