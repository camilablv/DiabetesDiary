package com.ca.records.glucose.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ca.designsystem.components.GlucoseRecordCard

@Composable
fun GlucoseRecordsPage(
    viewModel: GlucoseRecordsViewModel = hiltViewModel()
) {
    val records = viewModel.glucoseRecords().collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(records.itemCount) {
            records[it]?.let { record ->
                GlucoseRecordCard(record = record)
            }
        }
    }
}