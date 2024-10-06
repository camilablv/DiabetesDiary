package com.ca.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.home.presentation.viewmodel.HomeEvent
import com.ca.home.presentation.viewmodel.HomeViewState

@Composable
fun HomeContent(
    viewState: HomeViewState,
    onEvent: (HomeEvent) -> Unit
) {
    val context = LocalContext.current
    val focusRequester = FocusRequester()
    fun currentLocale() = context.resources.configuration.locales[0]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .focusRequester(focusRequester),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SingleRowCalendar(
            selectedDay = viewState.selectedDate,
            onSelectedDayChange = { onEvent(HomeEvent.SelectDate(it)) },
            locale = currentLocale()
        )
        LazyColumn(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
//            items(viewState.reminders) { reminder ->
//                when(reminder) {
//                    is RecordInsulinReminder -> {
//                        InsulinReminderTimelineCard(
//                            reminder = reminder,
//                            onDoneClick = {},
//                            onClick = { onEvent(HomeEvent.EditInsulinReminder(reminder.id)) },
//                        )
//                    }
//                    is RecordGlucoseReminder -> {
//                        GlucoseReminderTimelineCard(
//                            reminder = reminder,
//                            onAddClick = {  },
//                            onClick = { onEvent(HomeEvent.EditGlucoseReminder(reminder.id)) },
//                        )
//                    }
//                }
//            }
//
//            items(viewState.recordsByDate) { record ->
//                when(record) {
//                    is InsulinRecord -> {
//                        InsulinRecordTimelineCard(
//                            record = record,
//                            onClick = { onEvent(HomeEvent.EditInsulinRecord(record.id)) },
//                        )
//                    }
//                    is GlucoseRecord -> {
//                        GlucoseRecordTimelineCard(
//                            record = record,
//                            onClick = { onEvent(HomeEvent.EditGlucoseRecord(record.id)) },
//                        )
//                    }
//                }
//            }

            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(76.dp))
            }
        }
    }
}