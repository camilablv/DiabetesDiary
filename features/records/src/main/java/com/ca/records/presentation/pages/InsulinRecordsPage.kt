package com.ca.records.presentation.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.model.InsulinRecord
import com.ca.records.presentation.components.InsulinRecord

@Composable
fun InsulinRecordsPage(
    records: List<InsulinRecord?>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(records.size ?: 0) {
            records[it]?.let { record ->
                InsulinRecord(record = record)
            }
        }
    }
}