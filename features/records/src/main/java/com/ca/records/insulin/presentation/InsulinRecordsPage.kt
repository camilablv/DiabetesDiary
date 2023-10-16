package com.ca.records.insulin.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ca.designsystem.components.InsulinRecordCard

@Composable
fun InsulinRecordsPage(
    viewModel: InsulinRecordsViewModel = hiltViewModel()
) {
    val records = viewModel.insulinRecords().collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(records.itemCount) {
            records[it]?.let { record ->
                InsulinRecordCard(record = record)
            }
        } 
    }
}