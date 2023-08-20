package com.ca.designsystem.components.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
fun MainTopBar(
    title: String,
    navigateToSettings: () -> Unit
) {
    TopAppBar(
        backgroundColor = Theme.colors.surface,
        elevation = Theme.elevations.default,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 16.dp),
                text = title,
                style = Theme.typography.bodyLarge
            )

            IconButton(onClick = navigateToSettings) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
            }
        }

    }
}