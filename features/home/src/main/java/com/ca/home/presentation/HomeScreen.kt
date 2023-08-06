package com.ca.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.GlucoseRecordTimelineCard
import com.ca.designsystem.components.GlucoseReminderTimelineCard
import com.ca.designsystem.components.InsulinRecordTimelineCard
import com.ca.designsystem.components.InsulinReminderTimelineCard
import com.ca.designsystem.components.multifab.MultiFabItem
import com.ca.designsystem.components.multifab.MultiFloatingActionButton
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.designsystem.components.topbar.HomeTopBar
import com.ca.model.GlucoseRecord
import com.ca.model.InsulinRecord
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder

@Composable
fun HomeScreen(
    navigateToRecordGlucose: (String?) -> Unit,
    navigateToRecordInsulin: () -> Unit,
    navigateToInsulinReminder: () -> Unit,
    navigateToGlucoseReminder: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val focusRequester = FocusRequester()

    Scaffold(
        floatingActionButton = {
            MultiFloatingActionButton(
                modifier = Modifier,
                onMenuItemClicked = {
                    when(it) {
                        MultiFabItem.RecordInsulin -> { navigateToRecordInsulin() }
                        MultiFabItem.RecordGlucose -> { navigateToRecordGlucose(null) }
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            HomeTopBar(
                isInEditMode = viewState.isInEditMode,
                onEditClick = {
                    with(viewState.selectedItem) {
                        when(this) {
                            is RecordInsulinReminder -> { navigateToInsulinReminder() }
                            is RecordGlucoseReminder -> { navigateToGlucoseReminder() }
                            is InsulinRecord -> { navigateToRecordInsulin() }
                            is GlucoseRecord -> { navigateToRecordGlucose(this.id) }
                        }
                    }
                },
                onDeleteClick = {
                    with(viewState.selectedItem) {
                        when(this) {
                            is RecordInsulinReminder -> { viewModel.removeInsulinReminder(this) }
                            is RecordGlucoseReminder -> { viewModel.removeGlucoseReminder(this) }
                            is InsulinRecord -> { viewModel.removeInsulinRecord(this) }
                            is GlucoseRecord -> { viewModel.removeGlucoseRecord(this) }
                        }
                    }
                }
            )
        }
    ) { paddingValues ->

        BackHandler(enabled = true) {
            if (viewState.isInEditMode) {
                viewModel.disableEditMode()
            } else navigateBack()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { viewModel.disableEditMode() },
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SingleRowCalendar(
                selectedDay = viewState.selectedDate,
                onSelectedDayChange = {
                    viewModel.selectDate(it)
                    viewModel.disableEditMode()
                }
            )
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(
                    viewState.listItems
                ) { item ->
                    when(item) {
                        is RecordInsulinReminder -> {
                            InsulinReminderTimelineCard(
                                reminder = item,
                                selected = viewModel.isItemSelected(item),
                                onDoneClick = {
                                    viewModel.markInsulinReminderAsDone(it)
                                },
                                onClick = { viewModel.disableEditMode() },
                                onLongClick = { viewModel.enableEditMode(item) }
                            )
                        }
                        is RecordGlucoseReminder -> {
                            GlucoseReminderTimelineCard(
                                reminder = item,
                                selected = viewModel.isItemSelected(item),
                                onAddClick = { navigateToRecordGlucose(it.id.toString()) },
                                onClick = { viewModel.disableEditMode() },
                                onLongClick = { viewModel.enableEditMode(item) }
                            )
                        }
                        is InsulinRecord -> {
                            InsulinRecordTimelineCard(
                                record = item,
                                selected = viewModel.isItemSelected(item),
                                onClick = { viewModel.disableEditMode() },
                                onLongClick = { viewModel.enableEditMode(item) }
                            )
                        }
                        is GlucoseRecord -> {
                            GlucoseRecordTimelineCard(
                                record = item,
                                selected = viewModel.isItemSelected(item),
                                onClick = { viewModel.disableEditMode() },
                                onLongClick = { viewModel.enableEditMode(item) }
                            )
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
