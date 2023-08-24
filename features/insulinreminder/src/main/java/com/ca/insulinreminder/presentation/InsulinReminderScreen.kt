package com.ca.insulinreminder.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.Counter
import com.ca.designsystem.components.InsulinSelectionCard
import com.ca.designsystem.components.ReminderIterationOptions
import com.ca.designsystem.components.pickers.TimeWheelPicker
import com.ca.designsystem.theme.Theme

@Composable
fun InsulinReminderScreen(
    reminderId: Int?,
    viewModel: InsulinReminderViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {

    LaunchedEffect(reminderId != null) {
        viewModel.setupEditMode(reminderId!!)
    }

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
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
                InsulinSelectionCard(
                    modifier = Modifier,
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
                    onValueChanged = { viewModel.setUnits(it) }
                )
            }

            item {
                TimeWheelPicker(
                    modifier = Modifier,
                    time = viewState.time,
                    onSnappedTime = { viewModel.setTime(it) }
                )
            }

            item {
                ReminderIterationOptions(
                    selectedOption = viewState.iteration,
                    onSelect = { viewModel.setIteration(it) }
                )
            }
        }

        Button(
            onClick = {
                viewModel.addReminder()
                navigateBack()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Theme.colors.secondary
            ),
            shape = Theme.shapes.large,
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Add Reminder",
                color = Theme.colors.onSecondary
            )
        }
    }
}