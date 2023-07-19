package com.ca.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme
import com.ca.model.ReminderIteration
import com.ca.model.iterationOptions

@Composable
fun ReminderIterationOptions(
    selectedOption: ReminderIteration,
    onSelect: (ReminderIteration) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        iterationOptions.forEach {
            Text(
                modifier = Modifier
                    .background(
                        color = if (it == selectedOption) Theme.colors.secondary else Theme.colors.background,
                        shape = CircleShape
                    )
                    .border(1.dp, Color.Gray, CircleShape)
                    .padding(8.dp)
                    .clickable { onSelect(it) },
                text = it.text,
                style = Theme.typography.headlineSmall,
                color = if (it == selectedOption) Theme.colors.onSecondary else Theme.colors.onBackground
            )
        }
    }
}