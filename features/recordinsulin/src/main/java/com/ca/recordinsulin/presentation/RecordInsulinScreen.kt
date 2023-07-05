package com.ca.recordinsulin.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.*
import com.ca.designsystem.theme.Theme

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
        Column(
            modifier = Modifier
                .padding(paddings)
                .fillMaxSize()
                .imePadding(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
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
                        increment = { viewModel.incrementUnits() },
                        decrement = { viewModel.decrementUnits() },
                        onValueChanged = { viewModel.setUnits(it.toInt()) }
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

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Theme.colors.primary
                ),
                shape = Theme.shapes.large,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Add Record")
            }
        }
    }
}