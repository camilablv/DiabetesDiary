package com.ca.home.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.*
import com.ca.designsystem.components.multifab.MultiFabItem
import com.ca.designsystem.components.multifab.MultiFloatingActionButton
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.model.*

@Composable
fun HomeScreen(
    navigateToRecordGlucose: (String?) -> Unit,
    navigateToRecordInsulin: (String?) -> Unit,
    navigateToInsulinReminder: (String?) -> Unit,
    navigateToGlucoseReminder: (String?) -> Unit,
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
                        MultiFabItem.RecordInsulin -> { navigateToRecordInsulin(null) }
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
                            is RecordInsulinReminder -> { navigateToInsulinReminder(this.id.toString()) }
                            is RecordGlucoseReminder -> { navigateToGlucoseReminder(this.id.toString()) }
                            is InsulinRecord -> { navigateToRecordInsulin(this.id) }
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
                viewModel.setEditMode(false)
                viewModel.setSelectedItem(null)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    viewModel.setEditMode(false)
                    viewModel.setSelectedItem(null)
                },
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
                items(
                    viewState.listItems
                ) { item ->
                    when(item) {
                        is RecordInsulinReminder -> {
                            InsulinReminderTimelineCard(
                                reminder = item,
                                selected = item == viewState.selectedItem,
                                onDoneClick = {
                                    viewModel.markInsulinReminderAsDone(it)
                                },
                                onClick = {
                                    viewModel.setEditMode(false)
                                    viewModel.setSelectedItem(null)
                                },
                                onLongClick = {
                                    viewModel.setEditMode(true)
                                    viewModel.setSelectedItem(item)
                                }
                            )
                        }
                        is RecordGlucoseReminder -> {
                            GlucoseReminderTimelineCard(
                                reminder = item,
                                selected = item == viewState.selectedItem,
                                onAddClick = { navigateToRecordGlucose(it.id.toString()) },
                                onClick = {
                                    viewModel.setEditMode(false)
                                    viewModel.setSelectedItem(null)
                                },
                                onLongClick = {
                                    viewModel.setEditMode(true)
                                    viewModel.setSelectedItem(item)
                                }
                            )
                        }
                        is InsulinRecord -> {
                            InsulinRecordTimelineCard(
                                record = item,
                                selected = item == viewState.selectedItem,
                                onClick = {
                                    viewModel.setEditMode(false)
                                    viewModel.setSelectedItem(null)
                                },
                                onLongClick = {
                                    viewModel.setEditMode(true)
                                    viewModel.setSelectedItem(item)
                                }
                            )
                        }
                        is GlucoseRecord -> {
                            GlucoseRecordTimelineCard(
                                record = item,
                                selected = item == viewState.selectedItem,
                                onClick = {
                                    viewModel.setEditMode(false)
                                    viewModel.setSelectedItem(null)
                                },
                                onLongClick = {
                                    viewModel.setEditMode(true)
                                    viewModel.setSelectedItem(item)
                                }
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DismissBackground(dismissState: DismissState) {
    if (dismissState.dismissDirection == DismissDirection.EndToStart) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                ,
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.Gray.copy(alpha = 0.5f), CircleShape),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "",
                        tint = Color.White
                    )
                }

                IconButton(
                    modifier = Modifier
                        .size(36.dp)
                        .background(Color.Gray.copy(alpha = 0.5f), CircleShape),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }

    }
}
