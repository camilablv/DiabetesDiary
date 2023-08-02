package com.ca.home.presentation

import androidx.compose.foundation.layout.*
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
import com.ca.designsystem.components.*
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.designsystem.components.multifab.MultiFabItem
import com.ca.designsystem.components.multifab.MultiFloatingActionButton
import com.ca.model.*

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SingleRowCalendar(
                selectedDay = viewState.selectedDate,
                onSelectedDayChange = { viewModel.selectDate(it) }
            )
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(viewState.reminders) { reminder ->
                    when(reminder) {
                        is RecordInsulinReminder -> {
                            InsulinReminderCardWithCheckbox(
                                reminder = reminder,
                                onDoneClick = {}
                            )
                        }
                        is RecordGlucoseReminder -> {
                            GlucoseReminderCardWithCheckbox(
                                reminder = reminder,
                                onDoneClick = {}
                            )
                        }
                    }
                }

                items(viewState.records) { item: Record ->
                    when(item) {
                        is InsulinRecord -> {
                            InsulinRecordCardWithTimeline(record = item)
                        }
                        is GlucoseRecord -> {
                            GlucoseRecordCardWithTimeline(record = item)
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp))
                }
            }
        }
    }
}
