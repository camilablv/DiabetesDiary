package com.ca.home.presentation

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.*
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.designsystem.components.multifab.MultiFabItem
import com.ca.designsystem.components.multifab.MultiFloatingActionButton
import com.ca.model.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navigateToRecordGlucose: (String?) -> Unit,
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
                        MultiFabItem.RecordGlucose -> { navigateToRecordGlucose(null) }
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
                items(
                    viewState.listItems,
                    key = { it.time }
                ) { item ->
                    val dismissState = rememberDismissState()

                    SwipeToDismiss(
                        state = dismissState,
                        modifier = Modifier,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(0.2f)},
                        background = {
                            DismissBackground(dismissState = dismissState)
                        }
                    ) {
                        when(item) {
                            is RecordInsulinReminder -> {
                                InsulinReminderTimelineCard(
                                    reminder = item,
                                    onDoneClick = {
                                        viewModel.markInsulinReminderAsDone(it)
                                    }
                                )
                            }
                            is RecordGlucoseReminder -> {
                                GlucoseReminderTimelineCard(
                                    reminder = item,
                                    onAddClick = { navigateToRecordGlucose(it.id.toString()) }
                                )
                            }
                            is InsulinRecord -> {
                                InsulinRecordTimelineCard(record = item)
                            }
                            is GlucoseRecord -> {
                                GlucoseRecordTimelineCard(record = item)
                            }
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
