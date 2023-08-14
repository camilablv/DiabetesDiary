package com.ca.designsystem.components.fab

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
fun NewRecordFab(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Theme.colors.secondary,
        contentColor = Theme.colors.onSecondary
    ) {
        Icon(
            modifier = Modifier
                .size(26.dp),
            imageVector = Icons.Filled.Add,
            contentDescription = ""
        )
    }
}