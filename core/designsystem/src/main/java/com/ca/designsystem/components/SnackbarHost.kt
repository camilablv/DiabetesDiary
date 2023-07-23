package com.ca.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.theme.Theme

@Composable
fun DiarySnackbarHost(
    modifier: Modifier,
    snackbarHostState: SnackbarHostState,
    onDismiss: () -> Unit
) {

    SnackbarHost(
        modifier = modifier,
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier
                    .padding(16.dp),
                action = { data.actionLabel?.let {
                        TextButton(onClick = { onDismiss() }) {
                            Text(
                                text = data.message,
                                style = Theme.typography.bodyLarge,
                                color = Theme.colors.onBackground
                            )
                        }
                    }
                }
            ) {
                Text(
                    text = data.message,
                    style = Theme.typography.bodyLarge,
                    color = Theme.colors.onBackground
                )
            }
        }
    )
}