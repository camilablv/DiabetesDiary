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
import com.ca.designsystem.components.GlucoseRecordTimelineCard
import com.ca.designsystem.components.GlucoseReminderTimelineCard
import com.ca.designsystem.components.InsulinRecordTimelineCard
import com.ca.designsystem.components.InsulinReminderTimelineCard
import com.ca.designsystem.components.singlerowcalendar.SingleRowCalendar
import com.ca.home.presentation.viewmodel.HomeEvent
import com.ca.home.presentation.viewmodel.HomeViewState
import com.ca.model.GlucoseRecord
import com.ca.model.InsulinRecord
import com.ca.model.Record
import com.ca.model.RecordGlucoseReminder
import com.ca.model.RecordInsulinReminder
import com.ca.model.Reminder

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
            item {
                Reminders(
                    modifier = Modifier,
                    reminders = viewState.reminders,
                    editInsulinReminder = { onEvent(HomeEvent.EditInsulinReminder(it)) },
                    editGlucoseReminder = { onEvent(HomeEvent.EditGlucoseReminder(it)) },
                    onDoneInsulin = {},
                    onDoneGlucose = {}
                )
            }

            item {
                Records(
                    modifier = Modifier,
                    records = viewState.recordsByDate,
                    editInsulinRecord = { onEvent(HomeEvent.EditInsulinRecord(it)) },
                    editGlucoseRecord = { onEvent(HomeEvent.EditGlucoseRecord(it)) }
                )
            }

            item {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(76.dp))
            }
        }
    }
}

@Composable
fun Reminders(
    modifier: Modifier,
    reminders: List<Reminder>,
    editInsulinReminder: (Int) -> Unit,
    editGlucoseReminder: (Int) -> Unit,
    onDoneInsulin: (Int) -> Unit,
    onDoneGlucose: (Int) -> Unit,
) {
    Column {
        reminders.forEach { reminder ->
            when(reminder) {
                is RecordInsulinReminder -> {
                    InsulinReminderTimelineCard(
                        reminder = reminder,
                        onDoneClick = { onDoneInsulin(reminder.id) },
                        onClick = { editInsulinReminder(reminder.id) },
                    )
                }
                is RecordGlucoseReminder -> {
                    GlucoseReminderTimelineCard(
                        reminder = reminder,
                        onAddClick = { onDoneGlucose(reminder.id) },
                        onClick = { editGlucoseReminder(reminder.id) },
                    )
                }
            }
        }
    }
}

@Composable
fun Records(
    modifier: Modifier,
    records: List<Record>,
    editInsulinRecord: (String) -> Unit,
    editGlucoseRecord: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        records.forEach { record ->
            when(record) {
                is InsulinRecord -> {
                    InsulinRecordTimelineCard(
                        record = record,
                        onClick = { editInsulinRecord(record.id) },
                    )
                }
                is GlucoseRecord -> {
                    GlucoseRecordTimelineCard(
                        record = record,
                        onClick = { editGlucoseRecord(record.id) },
                    )
                }
            }
        }
    }
}
