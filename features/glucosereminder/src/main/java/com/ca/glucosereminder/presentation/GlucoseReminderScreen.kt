package com.ca.glucosereminder.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.ReminderIterationOptions
import com.ca.designsystem.components.TimeWheelPicker
import com.ca.designsystem.theme.Theme

@Composable
fun GlucoseReminderScreen(
    navigateBack: () -> Unit,
    viewModel: GlucoseReminderViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize(),
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
                    painter = painterResource(id = com.ca.designsystem.R.drawable.glucose_meter),
                    contentDescription = null)
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