package com.ca.designsystem.components.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
fun HomeTopBar(
    isInEditMode: Boolean,
    onEditClick: () -> Unit,
    onDeleteClick: () ->  Unit
) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = Theme.elevations.default,
        title = {
            Text(
                modifier = Modifier
                    .padding(start = 4.dp),
                text = "Diabetes Diary",
                style = Theme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        },
        actions = {
            AnimatedVisibility(
                visible = isInEditMode,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .fillMaxHeight()
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(36.dp),
                        onClick = onDeleteClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = ""
                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .size(36.dp),
                        onClick = onEditClick
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = ""
                        )
                    }
                }

            }
        },
    )
}