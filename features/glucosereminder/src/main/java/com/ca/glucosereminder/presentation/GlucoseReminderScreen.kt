package com.ca.glucosereminder.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.R
import com.ca.designsystem.components.ReminderIterationOptions
import com.ca.designsystem.components.pickers.TimeWheelPicker
import com.ca.designsystem.components.topbar.TopBar
import com.ca.designsystem.theme.Theme
import com.ca.model.ReminderIteration
import java.time.LocalTime

@Composable
fun GlucoseReminderRoute(
    reminderId: Int?,
    navigateBack: () -> Unit,
    viewModel: GlucoseReminderViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        if (reminderId == null) return@LaunchedEffect
        viewModel.setupEditMode(reminderId)
    }

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    GlucoseReminderScreen(
        topBarTitle = if (viewState.isInEditMode) "Edit Reminder" else "Add Reminder",
        navigateBack = navigateBack,
        viewState = viewState,
        setTime = viewModel::setTime,
        setIteration = viewModel::setIteration,
        submit = if (viewState.isInEditMode) viewModel::updateReminder else viewModel::addReminder
    )
}

@Composable
fun GlucoseReminderScreen(
    topBarTitle: String,
    navigateBack: () -> Unit,
    viewState: GlucoseReminderViewState,
    setTime: (LocalTime) -> Unit,
    setIteration: (ReminderIteration) -> Unit,
    submit: () -> Unit
) {
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
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier,
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Image(
                        modifier = Modifier
                            .size(300.dp),
                        painter = painterResource(id = R.drawable.glucose_meter),
                        contentDescription = null)
                }
                item {
                    TimeWheelPicker(
                        modifier = Modifier,
                        time = viewState.time,
                        onSnappedTime = { setTime(it) }
                    )
                }

                item {
                    ReminderIterationOptions(
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
                    backgroundColor = Theme.colors.secondary
                ),
                shape = Theme.shapes.large,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Save",
                    color = Theme.colors.onSecondary
                )
            }
        }
    }
}