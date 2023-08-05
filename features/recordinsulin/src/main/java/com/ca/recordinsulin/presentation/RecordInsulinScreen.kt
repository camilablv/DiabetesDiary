package com.ca.recordinsulin.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.common.utils.date
import com.ca.common.utils.timeOfHHmmPattern
import com.ca.designsystem.components.*
import com.ca.designsystem.components.pickers.DatePicker
import com.ca.designsystem.components.pickers.TimePicker
import com.ca.designsystem.theme.Theme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RecordInsulinScreen(
    viewModel: RecordInsulinViewModel = hiltViewModel(),
    navArgument: String?,
    onBackClick: () -> Unit
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val scaffoldState = rememberScaffoldState()
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(title = "Record Insulin", onBackClick = onBackClick) }
    ) { paddingValues ->

        TimePicker(
            expanded = viewState.showTimePicker,
            onDismiss = { viewModel.showTimePicker(false) },
            setTime = { viewModel.setTime(it) }
        )

        DatePicker(
            expanded = viewState.showDatePicker,
            onDismiss = { viewModel.showDatePicker(false) },
            setDate = { viewModel.setDate(it) }
        )

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .imePadding()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { focusManager.clearFocus(true) },
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
                                .width(120.dp)
                                .clickable { viewModel.showDatePicker(true) },
                            date = viewState.date.date()
                        )
                        TimeCard(
                            modifier = Modifier
                                .width(120.dp)
                                .clickable { viewModel.showTimePicker(true) },
                            time = viewState.time.timeOfHHmmPattern()
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
                        onDoneAction = {
                            viewModel.setNote(it)
                            keyboardController?.hide()
                            focusManager.clearFocus(true)
                        },
                        expandedMaxLines = 10,
                        collapsedMaxLines = 5
                    )
                }
            }

            Button(
                onClick = {
                    viewModel.addRecord()
                    onBackClick()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Theme.colors.primary
                ),
                shape = Theme.shapes.large,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Add Record",
                    color = Theme.colors.onPrimary
                )
            }
        }
    }
}