package com.ca.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.InsulinReminderCardWithCheckbox
import com.ca.designsystem.components.multifab.MultiFabItem
import com.ca.designsystem.components.multifab.MultiFloatingActionButton
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder

@Composable
fun HomeScreen(
    navigateToRecordGlucose: () -> Unit,
    navigateToRecordInsulin: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                modifier = Modifier,
                onMenuItemClicked = {
                    when(it) {
                        MultiFabItem.RecordInsulin -> { navigateToRecordInsulin() }
                        MultiFabItem.RecordGlucose -> { navigateToRecordGlucose() }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(viewState.reminders) { reminder ->
                when(reminder) {
                    is RecordInsulinReminder -> {
                        InsulinReminderCardWithCheckbox(reminder = reminder)
                    }
                    is RecordGlucoseReminder -> {

                    }
                }
            }
        }
    }

}