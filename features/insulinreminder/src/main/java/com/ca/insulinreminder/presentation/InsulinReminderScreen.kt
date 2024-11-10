package com.ca.insulinreminder.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.R
import com.ca.designsystem.components.AddInsulinButton
import com.ca.designsystem.components.Counter
import com.ca.designsystem.components.InsulinDropDownMenu
import com.ca.designsystem.components.Options
import com.ca.designsystem.components.pickers.TimeWheelPicker
import com.ca.designsystem.components.topbar.TopBar
import com.ca.designsystem.theme.Theme
import com.ca.model.Insulin
import com.ca.model.ReminderIteration
import com.ca.model.iterationOptions
import java.time.LocalTime

@Composable
fun InsulinReminderRoute(
    reminderId: Int?,
    viewModel: InsulinReminderViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    navigateToSettings: () -> Unit
) {

    LaunchedEffect(Unit) {
        if (reminderId == null) return@LaunchedEffect
        viewModel.setupEditMode(reminderId)
    }

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    InsulinReminderScreen(
        topBarTitle = if (viewState.isInEditMode) stringResource(id = R.string.edit_reminder) else stringResource(id = R.string.add_reminder),
        navigateBack = navigateBack,
        navigateToSettings = navigateToSettings,
        viewState = viewState,
        setTime = viewModel::setTime,
        setInsulinDropDownMenuExpanded = viewModel::setInsulinDropDownMenuExpanded,
        selectInsulin = viewModel::selectInsulin,
        incrementUnits = viewModel::incrementUnits,
        decrementUnits = viewModel::decrementUnits,
        setUnits = viewModel::setUnits,
        setIteration = viewModel::setIteration,
        submit = if (viewState.isInEditMode) viewModel::updateReminder else viewModel::addReminder
    )
}

@Composable
fun InsulinReminderScreen(
    topBarTitle: String,
    navigateBack: () -> Unit,
    navigateToSettings: () ->Unit,
    viewState: RecordsInsulinReminderViewState,
    setTime: (LocalTime) -> Unit,
    setInsulinDropDownMenuExpanded: (Boolean) -> Unit,
    selectInsulin: (Insulin) -> Unit,
    incrementUnits: () -> Unit,
    decrementUnits: () -> Unit,
    setUnits: (String) -> Unit,
    setIteration: (ReminderIteration) -> Unit,
    submit: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopBar(
                title = topBarTitle,
                onBackClick = { navigateBack() }
            )
        }
    ) { paddingValues ->
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
                    TimeWheelPicker(
                        modifier = Modifier,
                        time = viewState.time,
                        onSnappedTime = { setTime(it) }
                    )
                }
                item {
                    if (viewState.insulins.isEmpty()) {
                        AddInsulinButton { navigateToSettings() }
                    } else {
                        InsulinDropDownMenu(
                            modifier = Modifier
                                .clickable { if (viewState.insulins.isEmpty()) navigateToSettings() },
                            expanded = viewState.insulinDropDownMenuExpanded,
                            onExpandedChange = { setInsulinDropDownMenuExpanded(!viewState.insulinDropDownMenuExpanded) },
                            onSelect = { selectInsulin(it) },
                            onDismiss = { setInsulinDropDownMenuExpanded(false) },
                            selectedInsulin = viewState.selectedInsulin,
                            options = viewState.insulins
                        )
                    }
                }

                item {
                    Counter(
                        modifier = Modifier,
                        value = viewState.units,
                        increment = { incrementUnits() },
                        decrement = { decrementUnits() },
                        onValueChanged = { setUnits(it) }
                    )
                }

                item {
                    Options(
                        modifier = Modifier,
                        options = iterationOptions,
                        selectedOption = viewState.iteration,
                        onSelect = { setIteration(it) }
                    )
                }
            }

            Button(
                onClick = {
                    submit()
                    navigateBack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Theme.colors.secondary
                ),
                shape = Theme.shapes.large,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    color = Theme.colors.onSecondary
                )
            }
        }
    }
}

