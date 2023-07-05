package com.ca.recordinsulin.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.*

@Composable
fun RecordInsulinScreen(
    viewModel: RecordInsulinViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = "Record Insulin", onBackClick = onBackClick) }
    ) { paddings ->
        LazyColumn(
            modifier = Modifier
                .padding(paddings)
                .fillMaxSize(),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    DateCard(
                        modifier = Modifier
                            .width(120.dp),
                        date = viewState.date
                    )
                    TimeCard(
                        modifier = Modifier
                            .width(120.dp),
                        time = viewState.time
                    )
                }
            }

            item {
                InsulinSelectionCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                    expanded = viewState.insulinDropDownMenuExpanded,
                    onExpandedChange = { viewModel.setInsulinDropDownMenuExpanded(!viewState.insulinDropDownMenuExpanded) },
                    onSelect = { viewModel.selectInsulin(it) },
                    onDismiss = { viewModel.setInsulinDropDownMenuExpanded(false) },
                    selectedInsulin = viewState.selectedInsulin,
                    options = viewState.insulins
                )
            }

            item {
                Counter(
                    modifier = Modifier,
                    value = viewState.units,
                    increment = { /*TODO*/ },
                    decrement = { /*TODO*/ },
                    onValueChanged = {}
                )
            }

            item {
                NoteTextField(
                    value = viewState.note,
                    onValueChange = { viewModel.setNote(it) },
                    modifier = Modifier,
                    expanded = viewState.noteTextFieldExpanded,
                    placeholder = { Text(text = "Type note..", color = Color.Gray) },
                    onDoneAction = { /*TODO*/ },
                    expandedMaxLines = 10,
                    collapsedMaxLines = 5
                )
            }
        }
    }
}