package com.ca.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.*
import com.ca.designsystem.components.fab.NewRecordFab
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.designsystem.components.topbar.EditModeTopBar
import com.ca.model.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navigateToRecordGlucose: (String?) -> Unit,
    navigateToRecordInsulin: () -> Unit,
    navigateToInsulinReminder: () -> Unit,
    navigateToGlucoseReminder: () -> Unit,
    addRecord: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val editModeState by viewModel.editModeState.collectAsStateWithLifecycle()
    val focusRequester = FocusRequester()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    fun navigateToEditItem(item: ListItem?) {
        if (item == null) return
        when(item) {
            is RecordInsulinReminder -> { navigateToInsulinReminder() }
            is RecordGlucoseReminder -> { navigateToGlucoseReminder() }
            is InsulinRecord -> { navigateToRecordInsulin() }
            is GlucoseRecord -> { navigateToRecordGlucose(item.id) }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            EditModeTopBar(
                isInEditMode = editModeState.isInEditMode,
                onEditClick = { navigateToEditItem(editModeState.selectedItem) },
                onDeleteClick = { viewModel.removeItem(editModeState.selectedItem) }
            )
        },
        floatingActionButton = {
            NewRecordFab {
                addRecord()
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->

        BackHandler(enabled = editModeState.isInEditMode) {
            viewModel.disableEditMode()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
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
//                contentPadding = PaddingValues(vertical = 4.dp)
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
                        .height(76.dp))
                }
            }
        }
    }
}
