package com.ca.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
fun SettingsSectionCard(
    modifier: Modifier,
    sectionTitle: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = Theme.elevations.default,
        shape = Theme.shapes.medium,
        backgroundColor = Theme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = sectionTitle,
                style = Theme.typography.headlineSmall,
                modifier = Modifier,
                color = Theme.colors.onSurface
            )
            content()
        }
    }
}