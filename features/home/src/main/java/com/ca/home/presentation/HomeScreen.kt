package com.ca.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.GlucoseRecordTimelineCard
import com.ca.designsystem.components.GlucoseReminderTimelineCard
import com.ca.designsystem.components.InsulinRecordTimelineCard
import com.ca.designsystem.components.InsulinReminderTimelineCard
import com.ca.designsystem.components.fab.NewRecordFab
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.model.*

@Composable
fun HomeScreen(
    openRecordsMenuBottomSheet: () -> Unit,
    openInsulinRecordBottomSheet: (String) -> Unit,
    openInsulinReminderBottomSheet: (Int) -> Unit,
    openGlucoseRecordBottomSheet: (String) -> Unit,
    openGlucoseReminderBottomSheet: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val focusRequester = FocusRequester()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = { NewRecordFab { openRecordsMenuBottomSheet() } },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .focusRequester(focusRequester),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SingleRowCalendar(
                selectedDay = viewState.selectedDate,
                onSelectedDayChange = {
                    viewModel.selectDate(it)
                }
            )
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(
                    viewState.listItems
                ) { item ->
                    when(item) {
                        is RecordInsulinReminder -> {
                            InsulinReminderTimelineCard(
                                reminder = item,
                                onDoneClick = {
                                    viewModel.markInsulinReminderAsDone(it)
                                },
                                onClick = { openInsulinReminderBottomSheet(item.id) },
                            )
                        }
                        is RecordGlucoseReminder -> {
                            GlucoseReminderTimelineCard(
                                reminder = item,
                                onAddClick = {  },
                                onClick = { openGlucoseReminderBottomSheet(item.id) },
                            )
                        }
                        is InsulinRecord -> {
                            InsulinRecordTimelineCard(
                                record = item,
                                onClick = { openInsulinRecordBottomSheet(item.id) },
                            )
                        }
                        is GlucoseRecord -> {
                            GlucoseRecordTimelineCard(
                                record = item,
                                onClick = { openGlucoseRecordBottomSheet(item.id) },
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
